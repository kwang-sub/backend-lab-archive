#!/bin/bash

set -u

FORCE=false
WAIT_ON_EXIT=true
LAST_MESSAGE=""

print_message() {
  LAST_MESSAGE="$1"
  echo "$1"
}

pause_if_needed() {
  if [ "$WAIT_ON_EXIT" = true ]; then
    printf '\n종료하려면 Enter 키를 누르세요...'
    read -r _
  fi
}

error() {
  print_message "Error: $1" >&2
  pause_if_needed
  exit "${2:-1}"
}

finish() {
  print_message "$1"
  pause_if_needed
  exit 0
}

usage() {
  cat <<'EOF'
사용법:
  ./script/delete-worktree.sh [--force] [--wait] <worktree-name>

설명:
  현재 프로젝트의 외부 워크트리를 찾아 제거하거나, 끊어진 등록 정보는 prune으로 정리합니다.

옵션:
  --force, -f    변경 사항이 있어도 강제로 삭제합니다.
  --wait, -w     종료 전에 Enter 입력을 기다립니다.
EOF
  pause_if_needed
  exit 1
}

normalize_path() {
  local path="$1"

  if command -v cygpath >/dev/null 2>&1; then
    cygpath -u "$path" 2>/dev/null || printf '%s\n' "$path"
    return
  fi

  printf '%s\n' "$path" | sed -E 's#^([A-Za-z]):#/\L\1#; s#\\#/#g'
}

while [ $# -gt 0 ]; do
  case "$1" in
    --force|-f)
      FORCE=true
      shift
      ;;
    --wait|-w)
      WAIT_ON_EXIT=true
      shift
      ;;
    --help|-h)
      usage
      ;;
    -*)
      error "알 수 없는 옵션입니다: $1"
      ;;
    *)
      break
      ;;
  esac
done

if [ $# -eq 0 ]; then
  usage
fi

if ! command -v git >/dev/null 2>&1; then
  error "git 명령을 찾을 수 없습니다."
fi

WORKTREE_NAME="$1"

if [ -z "$WORKTREE_NAME" ]; then
  error "유효한 워크트리 이름을 입력해주세요."
fi

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
REPO_PARENT_DIR="$(dirname "$REPO_ROOT")"
REPO_NAME="$(basename "$REPO_ROOT")"
WORKTREE_BASE_PATH="$REPO_PARENT_DIR/worktree/$REPO_NAME"

if ! git -C "$REPO_ROOT" rev-parse --is-inside-work-tree >/dev/null 2>&1; then
  error "Git 저장소 내부에서만 실행할 수 있습니다."
fi

WORKTREE_LIST="$(git -C "$REPO_ROOT" worktree list --porcelain)" || error "워크트리 목록을 조회할 수 없습니다."
EXPECTED_WORKTREE_PATH="$WORKTREE_BASE_PATH/$WORKTREE_NAME"

REGISTERED_PATH=""
REGISTERED_NORMALIZED_PATH=""
PRUNABLE=false
CURRENT_PATH=""

while IFS= read -r line; do
  case "$line" in
    worktree\ *)
      CURRENT_PATH="${line#worktree }"
      CURRENT_NORMALIZED_PATH="$(normalize_path "$CURRENT_PATH")"
      CURRENT_BASENAME="$(basename "$CURRENT_NORMALIZED_PATH")"
      EXPECTED_PREFIX="$WORKTREE_BASE_PATH/"

      if [ "$CURRENT_BASENAME" = "$WORKTREE_NAME" ] && [[ "$CURRENT_NORMALIZED_PATH" == "$EXPECTED_PREFIX"* ]]; then
        REGISTERED_PATH="$CURRENT_PATH"
        REGISTERED_NORMALIZED_PATH="$CURRENT_NORMALIZED_PATH"
        PRUNABLE=false
      fi
      ;;
    prunable*)
      if [ -n "$REGISTERED_PATH" ] && [ "$CURRENT_PATH" = "$REGISTERED_PATH" ]; then
        PRUNABLE=true
      fi
      ;;
    "")
      CURRENT_PATH=""
      ;;
  esac
done <<< "$WORKTREE_LIST"

if [ -z "$REGISTERED_PATH" ]; then
  if [ -d "$EXPECTED_WORKTREE_PATH" ]; then
    rm -rf "$EXPECTED_WORKTREE_PATH" || error "등록되지 않은 워크트리 디렉터리를 삭제하지 못했습니다: $EXPECTED_WORKTREE_PATH"

    git -C "$REPO_ROOT" worktree prune >/dev/null 2>&1 || error "워크트리 정리(prune)에 실패했습니다."

    if [ -d "$WORKTREE_BASE_PATH" ] && [ -z "$(ls -A "$WORKTREE_BASE_PATH")" ]; then
      rmdir "$WORKTREE_BASE_PATH" >/dev/null 2>&1 || error "빈 워크트리 프로젝트 디렉터리를 삭제하지 못했습니다: $WORKTREE_BASE_PATH"
    fi

    finish "Git 등록은 없지만 남아 있던 워크트리 디렉터리를 삭제했습니다: $EXPECTED_WORKTREE_PATH"
  fi

  error "Git에 등록된 워크트리를 찾을 수 없습니다: $WORKTREE_NAME"
fi

if [ "$REGISTERED_NORMALIZED_PATH" = "$REPO_ROOT" ]; then
  error "메인 저장소는 삭제할 수 없습니다."
fi

if [ "$PRUNABLE" = true ] || [ ! -e "$REGISTERED_NORMALIZED_PATH" ]; then
  git -C "$REPO_ROOT" worktree prune >/dev/null 2>&1 || error "끊어진 워크트리 정리(prune)에 실패했습니다."
  finish "끊어진 워크트리 등록을 정리했습니다: $REGISTERED_PATH"
fi

REMOVE_ARGS=()

if [ "$FORCE" = true ]; then
  REMOVE_ARGS+=(--force)
fi

if ! git -C "$REPO_ROOT" worktree remove "${REMOVE_ARGS[@]}" "$REGISTERED_PATH"; then
  if [ "$FORCE" = true ]; then
    error "워크트리 강제 삭제에 실패했습니다: $REGISTERED_PATH"
  fi

  error "워크트리 삭제에 실패했습니다. 변경 사항이 있다면 --force 옵션을 사용해주세요: $REGISTERED_PATH"
fi

git -C "$REPO_ROOT" worktree prune >/dev/null 2>&1 || error "워크트리 정리(prune)에 실패했습니다."

if [ -d "$WORKTREE_BASE_PATH" ] && [ -z "$(ls -A "$WORKTREE_BASE_PATH")" ]; then
  rmdir "$WORKTREE_BASE_PATH" >/dev/null 2>&1 || error "빈 워크트리 프로젝트 디렉터리를 삭제하지 못했습니다: $WORKTREE_BASE_PATH"
fi

finish "워크트리가 성공적으로 삭제되었습니다: $REGISTERED_PATH"
