import matplotlib.pyplot as plt

MAX_SUBPLOTS = 4


class PieVisualizer:
    def __init__(self, votings_names):
        self.votings_names = votings_names
        self.votes = {}
        self.fig, self.ax = plt.subplots()
        self.ax.axis('equal')
        self.plot('')

    def update_votes(self, title, votes):
        self.votes = votes
        self.plot(title)

    def plot(self, title):
        labels, results = dict_to_labels_and_values(self.votes)
        plt.cla()
        self.ax.pie(results, labels=labels, autopct='%1.1f%%', shadow=True, startangle=90)
        plt.title(title)
        plt.pause(0.1)


def dict_to_labels_and_values(d):
    labels = []
    values = []
    for key, value in d.items():
        labels.append(key)
        values.append(value)
    return labels, values
