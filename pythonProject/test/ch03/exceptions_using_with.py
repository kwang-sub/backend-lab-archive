import sys

with open("poem.txt") as f:
    for line in f:
        print(line)


print(sys.version_info)
print(sys.version_info.major)