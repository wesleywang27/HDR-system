import mnist_loader

import time
from sklearn.externals import joblib
from sklearn.linear_model import LogisticRegression


def lr_baseline():
    print(time.strftime('%Y-%m-%d %H:%M:%S'))
    training_data, validation_data, test_data = mnist_loader.load_data()
    lr_clf = LogisticRegression(penalty='l2', solver ='lbfgs', multi_class='multinomial', max_iter=800,  C=0.2 )
    lr_clf.fit(training_data[0], training_data[1])

    print(time.strftime('%Y-%m-%d %H:%M:%S'))
    joblib.dump(lr_clf, 'model/lr_clf.model')

if __name__ == "__main__":
    lr_baseline()