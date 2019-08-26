import sys
import dlib
import numpy as np
import scipy.misc
import imageio 
import os
import cv2
import csv
from PIL import Image

train_image_path = sys.argv[1]
train_image_name = sys.argv[2]

TOLERANCE = 0.6

face_detector = dlib.get_frontal_face_detector()
shape_predictor = dlib.shape_predictor(train_image_path+"shape_predictor_68_face_landmarks.dat")
face_recognition_model = dlib.face_recognition_model_v1(train_image_path+"dlib_face_recognition_resnet_model_v1.dat")

def get_face_encodings(path_to_image):
        #print(path_to_image)
        im = Image.open(path_to_image)
        #image = imageio.imread(path_to_image)
        #image = im.convert('RGB')
        rgb_im = im.convert('RGB')
        rgb_im.save(path_to_image)
        image = imageio.imread(path_to_image)
        #print(type(image))
        detected_faces = face_detector(image, 1)
        shapes_faces = [shape_predictor(image, face) for face in detected_faces]
        return [np.array(face_recognition_model.compute_face_descriptor(image, face_pose, 1)) for face_pose in shapes_faces]

def compare_face_encodings(known_faces, face):
        return (np.linalg.norm(known_faces - face, axis=1) <= TOLERANCE)

def find_match(known_faces, names, face):
        matches = compare_face_encodings(known_faces, face)
        count = 0
        for match in matches:
                if match:
                        return names[count]
                count += 1
        return 'Not_Found'

def val():
        face_encodings_in_image = get_face_encodings(train_image_path+train_image_name)
        s = ",".join([str(x) for x in face_encodings_in_image[0]])
        #print(s)
        #face_encodings.append(face_encodings_in_image[0])
        with open(train_image_path+'data.csv', 'a') as f:  
            f.write(train_image_name[:-4]+","+s+'\n')
if __name__ == "__main__":
	val()
