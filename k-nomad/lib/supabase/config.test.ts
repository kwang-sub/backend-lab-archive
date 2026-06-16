import assert from "node:assert/strict"
import test from "node:test"

import {
  getSupabaseConfig,
  getSupabaseConfigResult,
  getSupabaseUserConfigErrorMessage,
  isSupabaseConfigured,
  SupabaseConfigError,
} from "./config"

const VALID_ENV = {
  NEXT_PUBLIC_SUPABASE_URL: "https://demo-project.supabase.co",
  NEXT_PUBLIC_SUPABASE_ANON_KEY: "demo-anon-key",
}

test("Supabase 설정이 없으면 invalid 결과를 반환한다", () => {
  const result = getSupabaseConfigResult({})

  assert.equal(result.isValid, false)

  if (!result.isValid) {
    assert.match(result.reason, /NEXT_PUBLIC_SUPABASE_URL/)
  }
})

test("Dashboard URL이 들어오면 invalid 결과를 반환한다", () => {
  const result = getSupabaseConfigResult({
    NEXT_PUBLIC_SUPABASE_URL: "https://supabase.com/dashboard/project/_/settings/api",
    NEXT_PUBLIC_SUPABASE_ANON_KEY: "demo-anon-key",
  })

  assert.equal(result.isValid, false)

  if (!result.isValid) {
    assert.match(result.reason, /Dashboard 주소/)
  }
})

test("프로젝트 URL과 anon key가 있으면 valid 결과를 반환한다", () => {
  const result = getSupabaseConfigResult(VALID_ENV)

  assert.deepEqual(result, {
    isValid: true,
    config: {
      url: VALID_ENV.NEXT_PUBLIC_SUPABASE_URL,
      anonKey: VALID_ENV.NEXT_PUBLIC_SUPABASE_ANON_KEY,
    },
  })
})

test("invalid 설정으로 getSupabaseConfig를 호출하면 명시적 예외를 던진다", () => {
  assert.throws(
    () => getSupabaseConfig({ NEXT_PUBLIC_SUPABASE_URL: "not-a-url", NEXT_PUBLIC_SUPABASE_ANON_KEY: "key" }),
    SupabaseConfigError,
  )
})

test("사용자용 설정 오류 메시지는 설정 누락 시에만 노출된다", () => {
  assert.equal(getSupabaseUserConfigErrorMessage({}), "Supabase 환경변수가 올바르게 설정되지 않았습니다. .env.local 값을 확인해주세요.")
  assert.equal(getSupabaseUserConfigErrorMessage(VALID_ENV), null)
  assert.equal(isSupabaseConfigured(VALID_ENV), true)
  assert.equal(isSupabaseConfigured({}), false)
})
