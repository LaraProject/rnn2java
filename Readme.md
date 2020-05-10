### Instructions

## Setup OS

1. Install protobuf
```
sudo apt install protobuf-compiler
```

2. Install maven
```
sudo apt-get install maven
```

## Setup Python environnement
```
$ virtualenv venv
$ source venv/bin/activate
$ pip install protobuf tensorflow numpy
```

## Setup Java environnement
```
$ mvn package
```

## Compile protobuf
```
$ make
```

## Run everything

1. Launch the server (*make sure to use the venv environnement*)
```
$ python python/main.py
```
2. In an other window
```
$ java -jar target/RNN2Java-0.0.1-SNAPSHOT.jar
```