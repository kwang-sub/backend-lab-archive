import { describe, expect, it, jest } from "@jest/globals"
import { render, screen } from "@testing-library/react"
import type { ReactNode } from "react"
import CityDetailPage from "@/app/cities/[slug]/page"

const notFound = jest.fn(() => {
  throw new Error("NEXT_NOT_FOUND")
})

jest.mock("next/link", () => {
  return function MockLink({
    children,
    href,
    className,
  }: {
    children: ReactNode
    href: string
    className?: string
  }) {
    return (
      <a href={href} className={className}>
        {children}
      </a>
    )
  }
})

jest.mock("next/navigation", () => ({
  notFound: () => notFound(),
}))

describe("CityDetailPage", () => {
  it("유효한 도시 id로 상세 페이지를 렌더링한다", async () => {
    const page = await CityDetailPage({
      params: Promise.resolve({ slug: "jeju" }),
    })

    render(page)

    expect(screen.getByRole("heading", { name: "제주" })).toBeTruthy()
    expect(screen.getByText("자연경관과 원격 근무 환경을 동시에 누릴 수 있는 섬 도시")).toBeTruthy()
    expect(screen.getByText("추천 계절 겨울")).toBeTruthy()
  })

  it("존재하지 않는 도시 id는 notFound를 호출한다", async () => {
    notFound.mockClear()

    await expect(
      CityDetailPage({
        params: Promise.resolve({ slug: "missing-city" }),
      }),
    ).rejects.toThrow("NEXT_NOT_FOUND")
    expect(notFound).toHaveBeenCalled()
  })
})
