import resultsReceiver


def main():
    votings_number = get_votings_number()
    votings_names = get_votings_names(votings_number)
    receiver = resultsReceiver.ResultsReceiver(votings_names)


def get_votings_number():
    while True:
        try:
            n = input('How many votings do you want to subscribe?  > ')
            num = int(n)
            if num <= 0:
                print('Type positive number!')
                continue
            return num
        except ValueError:
            print('ValueError! Try again.')


def get_votings_names(num):
    votings_names = []
    for i in range(0, num):
        while True:
            name = input('Type ' + str(i + 1) + ' voting name:\t> ')
            if name == '':
                print('Name cannot be empty! Try again.')
                continue
            if votings_names.__contains__(name):
                print('You have already type that voting! Try again')
                continue
            votings_names.append(name)
            break
    return votings_names


if __name__ == '__main__':
    main()