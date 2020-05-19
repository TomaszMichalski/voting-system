import matplotlib.pyplot as plt


class PieVisualizer:
    def __init__(self, votes):
        self.votes = votes
        self.fig, self.ax = plt.subplots()
        self.ax.axis('equal')
        self.plot()

    def update_votes(self, votes):
        self.votes = votes
        self.plot()

    def plot(self):
        labels, results = dict_to_labels_and_values(self.votes)
        plt.cla()
        self.ax.pie(results, labels=labels, autopct='%1.1f%%', shadow=True, startangle=90)
        plt.pause(0.1)


def dict_to_labels_and_values(d):
    labels = []
    values = []
    for key, value in d.items():
        labels.append(key)
        values.append(value)
    return labels, values
