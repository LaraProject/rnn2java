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

## Compile protobuf
```
$ make
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

## Run everything

Install the models as follows
```  
models/##/model_enc.h5
models/##/tokenizer.pickle
models/##/model_dec.h5
```
where `##` is the number of the personn answering (number of the model).

1. Launch the server (*make sure to use the venv environnement*)
```
$ python python/main.py
```
2. Run the client in another terminal
```
$ java -jar target/RNN2Java-0.0.1-SNAPSHOT.jar
```