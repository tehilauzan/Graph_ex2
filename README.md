# What Breast cancer will recur?

1. # perceptron algorithm

2. ## Adaline algorithm 

```python
import numpy
import pandas
import matplotlib.pyplot as pyplot
import time
from sklearn.model_selection import train_test_split,cross_val_score
from sklearn.preprocessing import StandardScaler
from sklearn import metrics

#read the data, and return all nulls to be '?'

```python
data = pandas.read_csv('wpbc.data', names= range(1,36), index_col= False, na_values="?")

data[2].replace({'N':-1,'R':1},inplace=True)
```
#delete columns 1+3
```python
data = data.drop([1, 3], axis = 1)
```
#devided the data to two class (33% and 66%%)
scaler = StandardScaler()

X = scaler.fit_transform(data.iloc[:, 1:].values)
y = data.iloc[:, 0].values

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.33, random_state = 1 )

freqs = pandas.DataFrame({"Training dataset": [(y_train == 1).sum(),(y_train == -1).sum()],
                      "Test dataset": [(y_test == 1).sum(),(y_test == -1).sum()],
                      "Total": [(y_train == 1).sum()+(y_test == 1).sum(),(y_train == -1).sum()+(y_test == -1).sum()]},
                     index=["Recurrent", "Nonrecurrent"])
freqs[["Training dataset", "Test dataset", "Total"]]

from numpy.random import seed

class Adaline(object):
    """Adaptive Linear Neuron Classifier.

    Parameters:
    ------------
    learning_rate : The learning rate (between 0 and 1)
    num_iterations : Passes over the training dataset.
    """

    def __init__(self, learning_rate=0.01, num_iterations=10, random_state=None):
        self.num_iterations = num_iterations
        self.learning_rate = learning_rate
        self.w_initialized = False
        if random_state:
            seed(random_state)  # Set random state for shuffling and initializing the weights

    def get_params(self, deep=True):
        return {"learning_rate": self.learning_rate, "num_iterations": self.num_iterations}

    def fit(self, X, y):
        """ Fit training data.

        Parameters
        ----------
        X : shape = [n_samples, n_features]. Training data, where n_samples is the number of samples and
            n_features is the number of features.
        y : shape = [n_samples]. Target values.
        """
        self.init_weights(X.shape[1])
        self.cost_ = []
        for i in range(self.num_iterations):
            sum = 0
            for xi, target in zip(X, y):
                sum = sum +self.update_weights(xi, target)
            avg_cost = sum / len(y)
            self.cost_.append(avg_cost)
        return self

    def partial_fit(self, X, y):
        """Fit training data without reinitializing the weights"""
        if not self.w_initialized:
            self.init_weights(X.shape[1])
        if y.ravel().shape[0] > 1:
            for xi, target in zip(X, y):
                self.update_weights(xi, target)
        else:
            self.update_weights(X, y)
        return self

    def init_weights(self, m):
        """Initialize weights to zeros"""
        self.w_ = numpy.zeros(1 + m)
        self.w_initialized = True

    def update_weights(self, xi, target):
        """Apply Adaline learning rule to update the weights"""
        output = self.net_input(xi)
        error = target - output
        self.w_[1:] += self.learning_rate * xi.dot(error)
        self.w_[0] += self.learning_rate * error
        cost = 0.5 * error ** 2
        return cost

    def net_input(self, X):
        """Calculate net input"""
        weighted_sum = numpy.dot(X, self.w_[1:]) + self.w_[0]
        return weighted_sum

    def activation_function(self, X):
        return X

    def predict(self, X):
        """Return class label after unit step"""
        return numpy.where(self.activation_function(self.net_input(X)) >= 0.0, 1, -1)



i_range = range(1, 51)
j_range = [0.01,0.005,0.001,0.0005,0.0001]
i_scores = []

zdata = []
xdata = []
ydata = []

for i in i_range:
    for j in j_range:
        adaline_algo = Adaline(num_iterations=i, learning_rate=j, random_state=1)
        adaline_algo.fit(X_train, y_train)
        y_predict = adaline_algo.predict(X_test)
        scores = metrics.accuracy_score(y_test, y_predict)
        i_scores.append(scores)
        zdata.append(scores)
        xdata.append(i)
        ydata.append(j)

fig = pyplot.figure(figsize=(10,10))
ax = pyplot.axes(projection='3d')
ax.plot_trisurf(xdata, ydata, zdata, cmap='viridis');

start_time = time.time()
ada = Adaline(num_iterations=8, learning_rate=0.001, random_state=1)

ada.fit(X_train, y_train)
print("The time for model fitting is: %.2f sec" % (time.time() - start_time))

y_predict = ada.predict(X_train)
print("Accuracy of Adaline (train): %.2f percents" % (metrics.accuracy_score(y_train, y_predict)*100))

y_predict = ada.predict(X_test)
print("Accuracy of Adaline (split): %.2f percents" % (metrics.accuracy_score(y_test, y_predict)*100))
cv=cross_val_score(ada, X, y, cv=3, scoring='accuracy').mean()
print("Accuracy of Adaline (cross-validation): %.2f percents"  % (cv*100))


print("Standart Deviation of Adaline (cross-validation) %.2f precents" % (cross_val_score(ada, X, y, cv=3, scoring='accuracy').std()*100))


adaTime= time.time() - start_time
print("The time for getting all the model results: %.2f sec" % (adaTime))

```
  1. while we divide the set into two classes: 66% for training and 33% for testing. we saw we have a balancing problem. 
    so we devided the data so that will be enough patients with recurrent in the training set but also to have some in the testing
  

