import { describe, expect, it, jest } from "@jest/globals"
import { render, screen } from "@testing-library/react"
import type { ReactNode } from "react"
import Component from "@/crypto-ranking-board"

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

describe("Component", () => {
  it("각 도시 카드에 상세 페이지 링크를 렌더링한다", () => {
    render(<Component />)

    const detailLinks = screen.getAllByRole("link", { name: "상세 보기" })

    expect(detailLinks).toHaveLength(6)
    expect(detailLinks[0]).toHaveAttribute("href", "/cities/seoul")
  })

  it("도시 설명을 카드에 표시한다", () => {
    render(<Component />)

    expect(screen.getByText("도심 생활 인프라와 교통 접근성이 뛰어난 도시")).toBeTruthy()
  })
})
