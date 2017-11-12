#!/bin/bash
# A violent solution for normal account offline situation while drcom.py still running.

while true
do
    net_state=$(ping www.baidu.com -c 1 -W 3 | grep rtt)
    if [[ $net_state = '' ]]; then
        drcomPID=$(ps -ef | grep drcom | grep python2 | awk '{print $2}')
        if [[ $drcomPID ]]; then
            kill $drcomPID
        fi
        nohup python2 /drcom.py &>/dev/null & # change /drcom.py to your own path
    fi
    sleep 60
done
