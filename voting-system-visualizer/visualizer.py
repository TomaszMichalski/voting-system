import matplotlib.pyplot as plt
import numpy as np

MAX_SUBPLOTS = 4


class Visualizer:

    def __init__(self, votings_names, chart_type):
        self.votings_names = votings_names
        self.chart_type = chart_type

        self.votes = {}
        for i in range(0, len(votings_names)):
            self.votes[votings_names[i]] = {}
        self.initialize_plots(len(votings_names))
        self.plot_draft()

    def update_votes(self, title, votes):
        self.votes[title] = votes
        self.plot()

    def plot(self):
        for i in range(0, len(self.votings_names)):
            self.plot_chart(i)
        plt.pause(0.1)

    def plot_chart(self, n):
        if 'both' == self.chart_type:
            self.plot_pie_chart(self.ax[0][n], n)
            self.plot_bar_chart(self.ax[1][n], n)
        elif 'pie' == self.chart_type:
            self.plot_pie_chart(self.ax[n], n)
        elif 'bar' == self.chart_type:
            self.plot_bar_chart(self.ax[n], n)

    def plot_pie_chart(self, axis, n):
        axis.cla()
        axis.axis('equal')
        axis.set_title(self.votings_names[n])
        labels, results = dict_to_labels_and_values(self.votes[self.votings_names[n]])
        axis.pie(results, labels=labels, autopct='%1.1f%%', shadow=True, startangle=90)

    def plot_bar_chart(self, axis, n):
        axis.cla()
        axis.set_title(self.votings_names[n])
        axis.set_ylabel('Percents')
        labels, results = dict_to_sorted_labels_and_values(self.votes[self.votings_names[n]])
        x = np.arange(len(labels))
        axis.bar(x, results)
        axis.set_xticks(x)
        axis.set_xticklabels(tuple(labels))
        for j, v in enumerate(results):
            axis.text(j, v + 3, "{:.2f}".format(v) + '%', horizontalalignment='center')

    def plot_draft(self):
        self.plot()

    def initialize_plots(self, num):
        if 'both' == self.chart_type:
            self.fig, self.ax = plt.subplots(nrows=2, ncols=num)
            if 1 == num:
                self.ax[0] = [self.ax[0]]
                self.ax[1] = [self.ax[1]]
        else:
            self.fig, self.ax = plt.subplots(nrows=1, ncols=num)
            if 1 == num:
                self.ax = [self.ax]
        self.fig.suptitle('VOTINGS RESULTS')


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
            sorted_dict[key] /= totalSum
            sorted_dict[key] *= 100
    return dict_to_labels_and_values(sorted_dict)
