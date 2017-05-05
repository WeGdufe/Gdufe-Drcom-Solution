#!/usr/bin/env python
# coding=utf-8

import urllib2
import urllib

postdata = urllib.urlencode({
	'DDDDD':'学号',
	'upass':'密码',
	'0MKKey':'%25B5%25C7%25C2%25BC%2BLogin',
	'Submit':'%E7%99%BB%E9%99%86'
	})
toUrl = 'http://58.62.247.115'
result = urllib2.urlopen(toUrl,postdata)
#print result.read()

##### can login in many computer together #####
##### double click to login #####
##### need Python 2.7 #####
