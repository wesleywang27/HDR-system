import mnist_loader

# Third-party libraries
from sklearn import svm
from sklearn.externals import joblib
import time


def svm_baseline():
    print(time.strftime('%Y-%m-%d %H:%M:%S'))
    training_data, validation_data, test_data = mnist_loader.load_data()
    clf = svm.SVC(C=100.0, kernel='rbf', gamma=0.03, cache_size=8000, probability=False)
    clf.fit(training_data[0], training_data[1])

    print(time.strftime('%Y-%m-%d %H:%M:%S'))
    joblib.dump(clf, 'model/clf.model')


if __name__ == "__main__":
    svm_baseline()
