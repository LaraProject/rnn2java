#!/usr/bin/env python3
import socket
import select
from time import sleep
import message_pb2
from google.protobuf.internal import encoder

PORT = 9988

def recvall(sock):
    BUFF_SIZE = 4096 # 4 KiB
    data = b''
    while True:
        part = sock.recv(BUFF_SIZE)
        data += part
        if len(part) < BUFF_SIZE:
            # either 0 or end of data
            break
    return data

def answer(question):
    if (question == "hello"):
        return "hi"
    if (question == "good bye"):
        return "bye"

def answer_command(question):
    command = message_pb2.Command()
    command.type = message_pb2.Command.ANSWER
    command.name = 'ANSWER to "' + question + '"' 
    command.data = answer(question)
    return command

def main():
    # Connect over TCP socket
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind(('localhost', PORT))
    sock.listen(5)
    # Erase file
    cmd = message_pb2.Command()
    over = False
    while True and (not over):
        conn, addr = sock.accept()
        #conn.setblocking(0)
        while True:
            data = conn.recv(4096)
            if not data: break
            ready = select.select([conn], [], [], 1.0)
            if ready[0]:
                data += recvall(conn)
            cmd.ParseFromString(data)
            if (cmd.type == message_pb2.Command.CommandType.QUESTION):
                print("Question : '" + cmd.data + "' received.")
                conn.send(answer_command(cmd.data).SerializeToString())
                print("Question answered.")
                conn.close()
                break
            elif (cmd.type == message_pb2.Command.CommandType.SWITCH_PERSON):
                print("Error, only questions are accepted.")
                over = True
                conn.close()
                break
            elif (cmd.type == message_pb2.Command.CommandType.SWITCH_PERSON):
                print("Error, opcode not implemented yet.")
                over = True
                conn.close()
                break
            elif (cmd.type == message_pb2.Command.CommandType.SHUTDOWN):
                print("Quiting.")
                over = True
                conn.close()
                break
            sleep(1)
    sock.close()

if __name__ == '__main__':
    main()
