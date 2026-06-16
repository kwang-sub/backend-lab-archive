"use client"

import Link from "next/link"
import { useActionState } from "react"

import { signupAction } from "@/app/auth/actions"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"

const initialAuthActionState = {
  error: null,
}

export default function RegisterPage() {
  const [state, formAction, isPending] = useActionState(signupAction, initialAuthActionState)

  return (
    <main className="min-h-screen bg-gray-950 px-4 py-10 text-white">
      <div className="mx-auto flex w-full max-w-7xl justify-center">
        <Card className="w-full max-w-md border-gray-800 bg-gray-900">
          <CardHeader className="space-y-2">
            <CardTitle className="text-2xl text-white">회원가입</CardTitle>
            <CardDescription className="text-gray-400">
              이메일과 비밀번호로 계정을 생성한 뒤 대시보드로 이동합니다.
            </CardDescription>
          </CardHeader>
          <CardContent>
            <form className="space-y-5" action={formAction}>
              <div className="space-y-2">
                <Label htmlFor="email" className="text-gray-200">
                  이메일
                </Label>
                <Input
                  id="email"
                  name="email"
                  type="email"
                  placeholder="name@example.com"
                  className="border-gray-700 bg-gray-800 text-white placeholder:text-gray-500"
                />
              </div>

              <div className="space-y-2">
                <Label htmlFor="password" className="text-gray-200">
                  비밀번호
                </Label>
                <Input
                  id="password"
                  name="password"
                  type="password"
                  placeholder="8자 이상 입력"
                  className="border-gray-700 bg-gray-800 text-white placeholder:text-gray-500"
                />
              </div>

              <div className="space-y-2">
                <Label htmlFor="confirmPassword" className="text-gray-200">
                  비밀번호 확인
                </Label>
                <Input
                  id="confirmPassword"
                  name="confirmPassword"
                  type="password"
                  placeholder="비밀번호를 다시 입력하세요"
                  className="border-gray-700 bg-gray-800 text-white placeholder:text-gray-500"
                />
              </div>

              {state.error ? <p className="text-sm text-red-400">{state.error}</p> : null}

              <Button type="submit" className="w-full bg-orange-500 text-black hover:bg-orange-400" disabled={isPending}>
                {isPending ? "회원가입 중..." : "회원가입"}
              </Button>
            </form>

            <div className="mt-6 flex items-center justify-between text-sm text-gray-400">
              <Link href="/" className="hover:text-white">
                홈으로
              </Link>
              <p>
                이미 계정이 있나요?{" "}
                <Link href="/login" className="font-semibold text-orange-400 hover:text-orange-300">
                  로그인
                </Link>
              </p>
            </div>
          </CardContent>
        </Card>
      </div>
    </main>
  )
}
