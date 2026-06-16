import { redirect } from "next/navigation"
import Component from "../crypto-ranking-board"
import { logoutAction } from "@/app/auth/actions"
import { createClient } from "@/lib/supabase/server"

export default async function Page() {
  const supabase = await createClient()
  const {
    data: { user },
  } = await supabase.auth.getUser()

  if (!user) {
    redirect("/login")
  }

  return (
    <main className="relative">
      <div className="fixed right-4 top-4 z-50 flex items-center gap-2 rounded-lg border border-gray-700 bg-gray-900 px-3 py-2 text-xs text-gray-200 shadow-lg">
        <span className="max-w-48 truncate">{user.email}</span>
        <form action={logoutAction}>
          <button
            type="submit"
            className="rounded bg-orange-500 px-2 py-1 font-semibold text-black hover:bg-orange-400"
          >
            로그아웃
          </button>
        </form>
      </div>
      <Component />
    </main>
  )
}
