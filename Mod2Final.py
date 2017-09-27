list = ["xbox", "playstation", "pc", "apple"]


def list_o_matic(item):
    if not item:
        print(list.pop(), "Has been removed")
    else:
        if item in list:
            print(item + " will be removed")
            list.remove(item)
        else:
            print(item, "will be added to the list")
            list.append(item)
    return

#empty list = false
while list:
    print("List of items: ", list)
    stringIn = input("enter an item: ").lower()
    if stringIn == "quit":
        break
    else:
        list_o_matic(stringIn)

print("\nGoodbye!")
