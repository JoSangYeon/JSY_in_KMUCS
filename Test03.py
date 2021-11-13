''' Python implementation of QuickSort using Hoare's
partition scheme. '''

''' This function takes first element as pivot, and places
      all the elements smaller than the pivot on the left side
      and all the elements greater than the pivot on
      the right side. It returns the index of the last element
      on the smaller side '''


def partition(arr, low, high):
    pivot = arr[low]
    i = low - 1
    j = high + 1

    while (True):

        # Find leftmost element greater than
        # or equal to pivot
        i += 1
        while (arr[i] < pivot):
            i += 1

        # Find rightmost element smaller than
        # or equal to pivot
        j -= 1
        while (arr[j] > pivot):
            j -= 1

        # If two pointers met.
        if (i >= j):
            return j

        arr[i], arr[j] = arr[j], arr[i]


''' The main function that implements QuickSort
arr --> Array to be sorted,
low --> Starting index,
high --> Ending index '''


def quickSort(arr, low, high):
    ''' pi is partitioning index, arr[p] is now
    at right place '''
    if (low < high):
        pi = partition(arr, low, high)

        # Separately sort elements before
        # partition and after partition
        quickSort(arr, low, pi)
        quickSort(arr, pi + 1, high)


''' Function to pran array '''


def printArray(arr, n):
    for i in range(n):
        print(arr[i], end=" ")
    print()


# Driver code
arr = [9, 4, 7, 3, 9, 2, 5, 8, 1, 6]
n = len(arr)
quickSort(arr, 0, n - 1)
print("Sorted array:")
printArray(arr, n)

# This code is contributed by shubhamsingh10