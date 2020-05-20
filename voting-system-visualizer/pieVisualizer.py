import matplotlib.pyplot as plt

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
            self.ax[i].cla()
            self.ax[i].axis('equal')
            self.ax[i].set_title(self.votings_names[i])
            labels, results = dict_to_labels_and_values(self.votes[self.votings_names[i]])
            self.ax[i].pie(results, labels=labels, autopct='%1.1f%%', shadow=True, startangle=90)
        plt.pause(0.1)

    def plot_draft(self):
        self.plot('')

    def initialize_plots(self, num):
        self.fig, self.ax = plt.subplots(1, num)
        self.fig.suptitle('VOTINGS RESULTS')
        if 1 == num:
            self.ax = [self.ax]


def dict_to_labels_and_values(d):
    labels = []
    values = []
    for key, value in d.items():
        labels.append(key)
        values.append(value)
    return labels, values
