import matplotlib.pyplot as plt
import numpy as np

MAX_SUBPLOTS = 4


class PieVisualizer:
    def __init__(self, votings_names):
        self.votings_names = votings_names
        self.votes = {}
        for i in range(0, len(votings_names)):
            self.votes[votings_names[i]] = {}
        self.initialize_plots(len(votings_names))
        self.plot_draft()

    def update_votes(self, title, votes):
        self.votes[title] = votes
        self.plot(title)

    def plot(self, title):
        for i in range(0, len(self.votings_names)):
            self.ax[0][i].cla()
            self.ax[0][i].axis('equal')
            self.ax[0][i].set_title(self.votings_names[i])
            labels, results = dict_to_labels_and_values(self.votes[self.votings_names[i]])
            self.ax[0][i].pie(results, labels=labels, autopct='%1.1f%%', shadow=True, startangle=90)

            print('i: ', str(i))
            self.ax[1][i].cla()
            self.ax[1][i].set_ylabel('Percents')
            labels, results = dict_to_sorted_labels_and_values(self.votes[self.votings_names[i]])
            x = np.arange(len(labels))
            self.ax[1][i].bar(x, results)
            # plt.xticks(x, tuple(labels))
            self.ax[1][i].set_xticks(x)
            self.ax[1][i].set_xticklabels(tuple(labels))
            for j, v in enumerate(results):
                self.ax[1][i].text(j, v + 3, "{:.2f}".format(v) + '%', horizontalalignment='center')
        plt.pause(0.1)

    def plot_draft(self):
        self.plot('')

    def initialize_plots(self, num):
        self.fig, self.ax = plt.subplots(nrows=2, ncols=num)
        self.fig.suptitle('VOTINGS RESULTS')
        print('shape: ', self.ax.shape)
        # case 1 row
        # if 1 == num:
        #     self.ax = [self.ax]
        #case 2 rows:
        if 1 == num:
            self.ax[0] = [self.ax[0]]
            self.ax[1] = [self.ax[1]]



def dict_to_labels_and_values(d):
    labels = []
    values = []
    for key, value in d.items():
        labels.append(key)
        values.append(value)
    return labels, values

def dict_to_sorted_labels_and_values(d):
    sorted_dict = {k: v for k, v in sorted(d.items(), key=lambda item: item[1], reverse=True)}
    totalSum = sum(sorted_dict.values())
    if totalSum != 0:
        for key, value in sorted_dict.items():
        # for i in range(0, len(sorted_dict.values())):
            print('totalSum: ', totalSum)
            sorted_dict[key] /= totalSum
            sorted_dict[key] *= 100
    return dict_to_labels_and_values(sorted_dict)
