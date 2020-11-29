def change(amount):
    assert (amount >= 24 or amount == 20)

    if amount == 20:
        return [5, 5, 5, 5]
    if amount == 22:
        return [5, 5, 5, 7]
    if amount == 24:
        return [5, 5, 7, 7]
    if amount == 26:
        return [5, 7, 7, 7]
    if amount == 28:
        return [7, 7, 7, 7]

    coins = change(amount - 5)
    coins.append(5)
    return coins


amount = int(input())
print(change(amount))
