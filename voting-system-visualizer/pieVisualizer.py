import matplotlib.pyplot as plt

MAX_SUBPLOTS = 4


class PieVisualizer:
    def __init__(self, votings_names):
        self.votings_names = votings_names
        self.votes = {}
        self.fig, self.ax = plt.subplots(1, len(self.votings_names))
        self.fig.suptitle('VOTINGS RESULTS')
        if 1 == len(self.votings_names):
            self.ax = [self.ax]
        for i in range(0, len(self.votings_names)):
            self.ax[i].set_title(votings_names[i])
        self.plot('')

    def update_votes(self, title, votes):
        self.votes = votes
        self.plot(title)

    def plot(self, title):
        labels, results = dict_to_labels_and_values(self.votes)
        for i in range(0, len(self.votings_names)):
            self.ax[i].cla()
            self.ax[i].axis('equal')
            self.ax[i].set_title(self.votings_names[i])
            self.ax[i].pie(results, labels=labels, autopct='%1.1f%%', shadow=True, startangle=90)
        plt.pause(0.1)


def dict_to_labels_and_values(d):
    labels = []
    values = []
    for key, value in d.items():
        labels.append(key)
        values.append(value)
    return labels, values
