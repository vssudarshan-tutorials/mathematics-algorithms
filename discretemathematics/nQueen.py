import itertools as it


# Naive algorithm
def is_solution(permutation):
    for (i1, i2) in it.combinations(range(len(permutation)), 2):
        if abs(i1 - i2) == abs(permutation[i1] - permutation[i2]):
            return False

    return True


for perm in it.permutations(range(8)):
    if is_solution(perm):
        print(perm)
        exit()


