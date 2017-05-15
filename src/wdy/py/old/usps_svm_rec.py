import Image
from pylab import *
import numpy as np
from sklearn.externals import joblib
import os

count = sum([len(files) for root, dirs, files in os.walk('../../tmp/split/')])
lists = np.zeros((count, 256))

for num in range(0, count):
    img = Image.open('../../tmp/split/' + str(num) + '.jpg')
    img = img.resize((16, 16), Image.ANTIALIAS)
    arr = array(img.convert('L'), 'f')

    new_arr = []
    for row in arr:
        for i in row:
            #print i
            if i < 30:
                i = -1
            elif 30 <= i <= 128:
                i /= 255
                i -= 1
            elif i >= 225:
                i = 1
            else:
                i /= 255
            new_arr.append(i)
    new_arr = np.array(new_arr, dtype=np.float32, order="C", copy=True)
    lists[num] = new_arr

usps_clf = joblib.load('model/usps_clf.model')

predicted = usps_clf.predict(lists)

print(predicted)
