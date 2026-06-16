import { createServerClient } from "@supabase/ssr"
import { NextResponse, type NextRequest } from "next/server"
import { getSupabaseConfigResult } from "@/lib/supabase/config"

type CookieToSet = {
  name: string
  value: string
  options?: Record<string, unknown>
}

let hasLoggedConfigWarning = false

export async function updateSession(request: NextRequest) {
  let response = NextResponse.next({
    request,
  })

  const configResult = getSupabaseConfigResult()

  if (!configResult.isValid) {
    if (process.env.NODE_ENV !== "production" && !hasLoggedConfigWarning) {
      console.warn(`[supabase] ${configResult.reason}`)
      hasLoggedConfigWarning = true
    }

    return { response, user: null }
  }

  const supabase = createServerClient(
    configResult.config.url,
    configResult.config.anonKey,
    {
      cookies: {
        getAll() {
          return request.cookies.getAll()
        },
        setAll(cookiesToSet: CookieToSet[]) {
          cookiesToSet.forEach(({ name, value }) => request.cookies.set(name, value))

          response = NextResponse.next({
            request,
          })

          cookiesToSet.forEach(({ name, value, options }) => response.cookies.set(name, value, options as never))
        },
      },
    },
  )

  const {
    data: { user },
  } = await supabase.auth.getUser()

  return { response, user }
}
