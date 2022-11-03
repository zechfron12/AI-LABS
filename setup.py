import random

file_path = open('iris.data', 'r')


def read_file(file_path):
    rows = []
    for line in file_path:
        rows.append(line[:-1])
    return rows


def get_data(train_data):
    data = []
    for row in train_data:
        data.append(row.split(','))
    return data


def format_data(data):
    data_set = []
    for row in data:
        temp = []
        for i in range(len(row) - 1):
            temp.append(float(row[i]))
        data_set.append((temp, row[-1]))
    return data_set[:-1]


# write a method that separtes shuffled data into 2 sets
def separate_data(data_set):
    random.shuffle(data_set)
    train_set = data_set[:int(len(data_set) * 0.8)]
    test_set = data_set[int(len(data_set) * 0.8):]
    return train_set, test_set

def main():
    data = read_file(file_path)
    data = get_data(data)
    data_set = format_data(data)
    train_set, test_set = separate_data(data_set)
    print("Train set: ", len(train_set))
    print(train_set)
    print("Test set: ", len(test_set))
    print(test_set)

    input_layer = [0] * 4

    hidden_layer_weight = [[0] * 4] * 100
    output_layer_weight = [[0] * 100] * 3

    hidden_layer_bias = [0] * 100
    output_layer_bias = [0] * 3

    learning_rate = 0.1

main()
