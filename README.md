# What Breast cancer will recur?

1. # perceptron algorithm

```python
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt


def load_data():
    data = pd.read_csv('wpbc.data', names=range(1, 36), index_col=False, na_values="?")
    data[2].replace({'N': -1, 'R': 1}, inplace=True)
    # delete columns 1 +3
    data = data.drop([1, 3], axis=1)

    # make the dataset linearly separable
    data = data[:100]
    data[4] = np.where(data.iloc[:, -1] == 'Iris-setosa', 0, 1)
    data = np.asmatrix(data, dtype='float64')
    return data


data = load_data()


def perceptron(data, num_iter):
    features = data[:, :-1]
    labels = data[:, -1]

    # set weights to zero
    w = np.zeros(shape=(1, features.shape[1] + 1))

    misclassified_ = []

    for epoch in range(num_iter):
        misclassified = 0
        for x, label in zip(features, labels):
            x = np.insert(x, 0, 1)
            y = np.dot(w, x.transpose())
            target = 1.0 if (y > 0) else 0.0

            delta = (label.item(0, 0) - target)

            if (delta):  # misclassified
                misclassified += 1
                w += (delta * x)

        misclassified_.append(misclassified)
    return (w, misclassified_)


num_iter = 10
w, misclassified_ = perceptron(data, num_iter)


epochs = np.arange(1, num_iter+1)
plt.plot(epochs, misclassified_)
plt.xlabel('iterations')
plt.ylabel('misclassified')
plt.show()
```
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
```python
scaler = StandardScaler()
X = scaler.fit_transform(data.iloc[:, 1:].values)
y = data.iloc[:, 0].values
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.33, random_state = 1 )
freqs = pandas.DataFrame({"Training dataset": [(y_train == 1).sum(),(y_train == -1).sum()],
                      "Test dataset": [(y_test == 1).sum(),(y_test == -1).sum()],
                      "Total": [(y_train == 1).sum()+(y_test == 1).sum(),(y_train == -1).sum()+(y_test == -1).sum()]},
                     index=["Recurrent", "Nonrecurrent"])
freqs[["Training dataset", "Test dataset", "Total"]]
```
    
```python
class Adaline(object):
    def __init__(self, learning_rate=0.01, num_iterations=10, random_state=None):
        self.num_iterations = num_iterations
        self.learning_rate = learning_rate
        self.w_initialized = False
        if random_state:
            seed(random_state)  # Set random state for shuffling and initializing the weights

    def get_params(self, deep=True):
        return {"learning_rate": self.learning_rate, "num_iterations": self.num_iterations}

    def fit(self, X, y):
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
        if not self.w_initialized:
            self.init_weights(X.shape[1])
        if y.ravel().shape[0] > 1:
            for xi, target in zip(X, y):
                self.update_weights(xi, target)
        else:
            self.update_weights(X, y)
        return self

    def init_weights(self, m):
        self.w_ = numpy.zeros(1 + m)
        self.w_initialized = True

    def update_weights(self, xi, target):
        output = self.net_input(xi)
        error = target - output
        self.w_[1:] += self.learning_rate * xi.dot(error)
        self.w_[0] += self.learning_rate * error
        cost = 0.5 * error ** 2
        return cost

    def net_input(self, X):
        weighted_sum = numpy.dot(X, self.w_[1:]) + self.w_[0]
        return weighted_sum

    def activation_function(self, X):
        return X

    def predict(self, X):
        return numpy.where(self.activation_function(self.net_input(X)) >= 0.0, 1, -1)
```

looking for the best paremtars

```python
first_range = range(1, 41)
secend_range = [0.01,0.005,0.001,0.0005,0.0001]
first_scores = []

xdata = []
ydata = []
zdata = []

for i in first_range:
    for j in secend_range:
        adaline_algo = Adaline(num_iterations=i, learning_rate=j, random_state=1)
        adaline_algo.fit(X_train, y_train)
        y_predict = adaline_algo.predict(X_test)
        first_scores.append(metrics.accuracy_score(y_test, y_predict))
        xdata.append(i)
        ydata.append(j)
        zdata.append(metrics.accuracy_score(y_test, y_predict))

```
## The time for training the model using Adaline
```python
start_time = time.time()
adaline_algo = Adaline(num_iterations=8, learning_rate=0.001, random_state=1)

adaline_algo.fit(X_train, y_train)
print("time for fitting: %.2f sec" % (time.time() - start_time))
```
result- time for fitting: 0.04 sec

## The training Results
```python
y_predict = adaline_algo.predict(X_train)
print("training Results: %.2f percents" % (metrics.accuracy_score(y_train, y_predict)*100))
```
result- training Results: 78.79 percents
## The testing Results:
```python
y_predict = adaline_algo.predict(X_test)
print("Accuracy of Adaline algorithm with split: %.2f percents" % (metrics.accuracy_score(y_test, y_predict)*100))
cv=cross_val_score(adaline_algo, X, y, cv=3, scoring='accuracy').mean()
print("Accuracy of Adaline algorithm with cross-validation: %.2f percents"  % (cv*100))
print("Standart Deviation of Adaline algorithm with cross-validation %.3f precents" % (cross_val_score(adaline_algo, X, y, cv=3, scoring='accuracy').std()*100))
```
result- 
1.Accuracy of Adaline algorithm with split: 71.21 percents
2.Accuracy of Adaline algorithm with cross-validation: 76.26 percents
3.Standart Deviation of Adaline algorithm with cross-validation 2.575 precents

## The time that took to get all the model results:
```python
adalineFullTime= time.time() - start_time
print("The time that took to get all the model results: %.3f sec" % (adalineFullTime))

```
result- The time for getting all the model results: 0.27 sec

  1. while we divide the set into two classes: 66% for training and 33% for testing. we saw we have a balancing problem. 
    so we devided the data so that will be enough patients with recurrent in the training set but also to have some in the testing
  2. in this algorithm we were helped in this site: https://dzone.com/articles/adaline-explained-with-python-example-data-analyti
  

