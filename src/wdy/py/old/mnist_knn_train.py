import mnist_loader

import time
from sklearn.externals import joblib
from sklearn.neighbors import KNeighborsClassifier


def knn_baseline():
    print(time.strftime('%Y-%m-%d %H:%M:%S'))
    training_data, validation_data, test_data = mnist_loader.load_data()
    knn_clf = KNeighborsClassifier(n_neighbors=5, algorithm='kd_tree', weights='distance', p=3)
    knn_clf.fit(training_data[0], training_data[1])

    print(time.strftime('%Y-%m-%d %H:%M:%S'))
    joblib.dump(knn_clf, 'model/knn_clf.model')

if __name__ == "__main__":
    knn_baseline()