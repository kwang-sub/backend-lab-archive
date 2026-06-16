"use server"

import { redirect } from "next/navigation"
import { createClient } from "@/lib/supabase/server"
import { getSupabaseUserConfigErrorMessage } from "@/lib/supabase/config"

type AuthActionState = {
  error: string | null
}

function readCredential(formData: FormData, key: string) {
  const value = formData.get(key)
  return typeof value === "string" ? value.trim() : ""
}

export async function loginAction(_: AuthActionState, formData: FormData): Promise<AuthActionState> {
  const email = readCredential(formData, "email")
  const password = readCredential(formData, "password")

  if (!email || !password) {
    return { error: "이메일과 비밀번호를 입력해주세요." }
  }

  const configError = getSupabaseUserConfigErrorMessage()

  if (configError) {
    return { error: configError }
  }

  const supabase = await createClient()
  const { error } = await supabase.auth.signInWithPassword({ email, password })

  if (error) {
    return { error: "로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요." }
  }

  redirect("/")
}

export async function signupAction(_: AuthActionState, formData: FormData): Promise<AuthActionState> {
  const email = readCredential(formData, "email")
  const password = readCredential(formData, "password")
  const confirmPassword = readCredential(formData, "confirmPassword")

  if (!email || !password || !confirmPassword) {
    return { error: "모든 필드를 입력해주세요." }
  }

  if (password.length < 8) {
    return { error: "비밀번호는 8자 이상이어야 합니다." }
  }

  if (password !== confirmPassword) {
    return { error: "비밀번호 확인이 일치하지 않습니다." }
  }

  const configError = getSupabaseUserConfigErrorMessage()

  if (configError) {
    return { error: configError }
  }

  const supabase = await createClient()
  const { data, error } = await supabase.auth.signUp({
    email,
    password,
  })

  if (error) {
    return { error: "회원가입에 실패했습니다. 잠시 후 다시 시도해주세요." }
  }

  if (!data.session) {
    return { error: "회원가입은 완료됐지만 이메일 확인이 필요합니다. Supabase Auth 설정을 확인해주세요." }
  }

  redirect("/")
}

export async function logoutAction() {
  const configError = getSupabaseUserConfigErrorMessage()

  if (configError) {
    redirect("/login")
  }

  const supabase = await createClient()
  await supabase.auth.signOut()
  redirect("/login")
}
