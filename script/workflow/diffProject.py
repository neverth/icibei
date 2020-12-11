# coding=utf-8
import os
import paramiko

ssh = paramiko.SSHClient()

# 允许连接不在~/.ssh/known_hosts文件中的主机
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())

# 连接服务器
ssh.connect(hostname="106.14.44.108", port=22, username="root", password="Ly19980911")

# 执行命令，不要执行top之类的在不停的刷新的命令(可以执行多条命令，以分号来分隔多条命令)
stdin, stdout, stderr = ssh.exec_command("ls -la")

# 获取命令结果
res, err = stdout.read(), stderr.read()
stdin.write("pwd\n")
stdin.flush()q
result = res if res else err
res = result.decode()
res = result.decode("utf-8")
res = result.decode(encoding="utf-8")
print res

PROJECTS_DIR = [
    'authentication-server',
    'authorization-server',
    'game-server',
    'gateway-admin',
    'gateway-web',
    'icibei-web',
    'organization'
]


def get_modify_project():

    modify_project = []

    tags = os.popen('git tag').read().split('\n')
    tags.sort(reverse=True)

    modify_files = os.popen('git diff -X --dirstat=files  {} {}'.format(tags[0], tags[1])).read().split('\n')

    for modify_file in modify_files:

        if modify_file.find('%') == -1:
            continue

        dir_name = modify_file.split('%')[1].strip().split('/')[0]

        if dir_name in PROJECTS_DIR:
            modify_project.append(dir_name)

    return modify_project


if __name__ == '__main__':
    print get_modify_project()
    os.popen('cd ../../ && mvn package')
