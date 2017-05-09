# -*- coding: utf-8 -*-
from urllib import parse,request
from os import path
import time

def login(username, password):
	url = 'http://58.62.247.115/'
	header = {
	    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/6.1.2107.204 Safari/537.36"
	}
	data = {"DDDDD":username, "upass": password, "0MKKey":"%B5%C7%C2%BC+Login"}
	data = parse.urlencode(data).encode('utf-8')
	req = request.Request(url = url, data = data, headers = header)
	response = request.urlopen(req)


print("如果要更改用户名和密码，删除本目录下的cookie.txt文件即可\n")
print("一次输入用户名和密码后，下次就会自动登录\n")
print("本程序只适用于20m账号\n")
time.sleep(2)
if not path.isfile('cookie.txt'):
	username = input("请输入用户名：")
	password = input("请输入密码：")
	with open('cookie.txt', 'w') as w:
		w.write(username+password)
	login(username, password)
else:
	with open('cookie.txt', 'r') as f:
		string = f.read()
	username = string[0:11]
	password = string[11:]
	login(username, password)

print('登录应该成功，5s之后自动退出')
time.sleep(5)