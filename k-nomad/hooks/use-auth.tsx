"use client"

import { createContext, useCallback, useContext, useEffect, useReducer } from "react"
import type { AuthUser, LoginPayload } from "@/lib/auth-client"
import { getProfile, login as loginRequest, logout as logoutRequest } from "@/lib/auth-client"

interface AuthState {
  user: AuthUser | null
  token: string | null
  isLoading: boolean
  isInitialized: boolean
  error: string | null
}

type AuthAction =
  | { type: "LOGIN_START" }
  | { type: "LOGIN_SUCCESS"; payload: { user: AuthUser; token: string } }
  | { type: "LOGIN_ERROR"; payload: string }
  | { type: "LOGOUT" }
  | { type: "RESTORE_SUCCESS"; payload: { user: AuthUser; token: string } }
  | { type: "FINISH_INITIALIZE" }

const AUTH_STORAGE_KEY = "crypto-board-auth"

const initialState: AuthState = {
  user: null,
  token: null,
  isLoading: false,
  isInitialized: false,
  error: null,
}

function reducer(state: AuthState, action: AuthAction): AuthState {
  switch (action.type) {
    case "LOGIN_START":
      return { ...state, isLoading: true, error: null }
    case "LOGIN_SUCCESS":
      return {
        ...state,
        isLoading: false,
        isInitialized: true,
        user: action.payload.user,
        token: action.payload.token,
        error: null,
      }
    case "LOGIN_ERROR":
      return { ...state, isLoading: false, error: action.payload }
    case "LOGOUT":
      return { ...initialState, isInitialized: true }
    case "RESTORE_SUCCESS":
      return {
        ...state,
        user: action.payload.user,
        token: action.payload.token,
        isInitialized: true,
        error: null,
      }
    case "FINISH_INITIALIZE":
      return { ...state, isInitialized: true, isLoading: false }
    default:
      return state
  }
}

interface AuthContextValue extends AuthState {
  login: (payload: LoginPayload) => Promise<void>
  logout: () => Promise<void>
}

const AuthContext = createContext<AuthContextValue | null>(null)

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [state, dispatch] = useReducer(reducer, initialState)

  useEffect(() => {
    let isMounted = true

    const restoreSession = async () => {
      const stored = typeof window !== "undefined" ? localStorage.getItem(AUTH_STORAGE_KEY) : null
      if (!stored) {
        dispatch({ type: "FINISH_INITIALIZE" })
        return
      }

      try {
        const parsed = JSON.parse(stored) as { token?: string }
        if (!parsed?.token) {
          localStorage.removeItem(AUTH_STORAGE_KEY)
          dispatch({ type: "FINISH_INITIALIZE" })
          return
        }

        const profile = await getProfile(parsed.token)

        if (!isMounted) {
          return
        }

        dispatch({ type: "RESTORE_SUCCESS", payload: { user: profile, token: parsed.token } })
      } catch {
        localStorage.removeItem(AUTH_STORAGE_KEY)
        if (isMounted) {
          dispatch({ type: "FINISH_INITIALIZE" })
        }
      }
    }

    restoreSession()

    return () => {
      isMounted = false
    }
  }, [])

  const login = useCallback(async (payload: LoginPayload) => {
    dispatch({ type: "LOGIN_START" })
    try {
      const response = await loginRequest(payload)
      localStorage.setItem(AUTH_STORAGE_KEY, JSON.stringify({ token: response.token }))
      dispatch({ type: "LOGIN_SUCCESS", payload: response })
    } catch (error) {
      const message = error instanceof Error ? error.message : "로그인 중 문제가 발생했습니다."
      dispatch({ type: "LOGIN_ERROR", payload: message })
      throw error
    }
  }, [])

  const logout = useCallback(async () => {
    try {
      await logoutRequest()
    } finally {
      localStorage.removeItem(AUTH_STORAGE_KEY)
      dispatch({ type: "LOGOUT" })
    }
  }, [])

  const value: AuthContextValue = {
    ...state,
    login,
    logout,
  }

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
  const context = useContext(AuthContext)
  if (!context) {
    throw new Error("useAuth 훅은 AuthProvider 내부에서만 사용할 수 있습니다.")
  }
  return context
}
