const DASHBOARD_HOST = "supabase.com"
const DASHBOARD_PATH_PREFIX = "/dashboard"

export class SupabaseConfigError extends Error {
  constructor(message: string) {
    super(message)
    this.name = "SupabaseConfigError"
  }
}

export type SupabaseConfig = {
  url: string
  anonKey: string
}

type SupabaseConfigResult =
  | {
      isValid: true
      config: SupabaseConfig
    }
  | {
      isValid: false
      reason: string
    }

export function getSupabaseConfigResult(env: NodeJS.ProcessEnv = process.env): SupabaseConfigResult {
  const url = env.NEXT_PUBLIC_SUPABASE_URL?.trim()
  const anonKey = env.NEXT_PUBLIC_SUPABASE_ANON_KEY?.trim()

  if (!url) {
    return {
      isValid: false,
      reason: "NEXT_PUBLIC_SUPABASE_URL가 설정되지 않았습니다.",
    }
  }

  let parsedUrl: URL

  try {
    parsedUrl = new URL(url)
  } catch {
    return {
      isValid: false,
      reason: "NEXT_PUBLIC_SUPABASE_URL가 유효한 URL 형식이 아닙니다.",
    }
  }

  if (parsedUrl.host === DASHBOARD_HOST && parsedUrl.pathname.startsWith(DASHBOARD_PATH_PREFIX)) {
    return {
      isValid: false,
      reason: "NEXT_PUBLIC_SUPABASE_URL에는 Dashboard 주소가 아니라 프로젝트 API URL을 넣어야 합니다.",
    }
  }

  if (!anonKey) {
    return {
      isValid: false,
      reason: "NEXT_PUBLIC_SUPABASE_ANON_KEY가 설정되지 않았습니다.",
    }
  }

  return {
    isValid: true,
    config: {
      url,
      anonKey,
    },
  }
}

export function getSupabaseConfig(env: NodeJS.ProcessEnv = process.env): SupabaseConfig {
  const result = getSupabaseConfigResult(env)

  if (!result.isValid) {
    throw new SupabaseConfigError(result.reason)
  }

  return result.config
}

export function isSupabaseConfigured(env: NodeJS.ProcessEnv = process.env) {
  return getSupabaseConfigResult(env).isValid
}

export function getSupabaseUserConfigErrorMessage(env: NodeJS.ProcessEnv = process.env) {
  return isSupabaseConfigured(env)
    ? null
    : "Supabase 환경변수가 올바르게 설정되지 않았습니다. .env.local 값을 확인해주세요."
}
