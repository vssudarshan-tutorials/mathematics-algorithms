def moves(n):
    if n == 1:
        return 1

    return 2 * moves(n - 1) + 1


num = int(input())
print(moves(num))
