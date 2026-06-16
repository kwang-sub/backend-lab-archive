const MOCK_USER = {
  id: "user-1",
  email: "demo@cryptoboard.dev",
  name: "Crypto Analyst",
  role: "admin",
}

const MOCK_TOKEN = "mock-jwt-token"

const wait = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms))

export interface AuthUser {
  id: string
  email: string
  name: string
  role: string
}

export interface LoginPayload {
  email: string
  password: string
}

export interface AuthResponse {
  token: string
  user: AuthUser
}

export async function login({ email, password }: LoginPayload): Promise<AuthResponse> {
  await wait(600)

  const isValidUser = email === MOCK_USER.email && password === "demo1234"

  if (!isValidUser) {
    throw new Error("잘못된 이메일 또는 비밀번호입니다.")
  }

  return {
    token: MOCK_TOKEN,
    user: MOCK_USER,
  }
}

export async function logout(): Promise<void> {
  await wait(200)
}

export async function getProfile(token: string): Promise<AuthUser> {
  await wait(400)

  if (token !== MOCK_TOKEN) {
    throw new Error("세션이 만료되었습니다.")
  }

  return MOCK_USER
}
