# coding=utf-8
import os
from os.path import dirname, abspath
from qiniu import Auth, put_file

ABSPATH = dirname(abspath(__file__))

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

    # 修复git tag不全的bug
    os.popen('git fetch --tags').read()

    tags = os.popen('git tag').read().split('\n')
    tags.sort(reverse=True)

    # debug
    print os.popen('git tag').read()
    print tags
    print os.popen('git diff -X --dirstat=files  {} {}'.format(tags[0], tags[1])).read()

    modify_files = os.popen('git diff -X --dirstat=files  {} {}'.format(tags[0], tags[1])).read().split('\n')

    for modify_file in modify_files:

        if modify_file.find('%') == -1:
            continue

        dir_name = modify_file.split('%')[1].strip().split('/')[0]

        if dir_name in PROJECTS_DIR:
            modify_project.append(dir_name)

    return modify_project, tags[0]


def upload_file(paths):
    access_key = 'KS1_Wu6paDRLTcv5KYnuu-F_a8l-dzNKEsE-NzzQ'
    secret_key = '5xzwds96UAjZEtmCKeaOID7GbFToySajpJ7gvpBl'
    bucket_name = 'icibei-github-action'

    # 构建鉴权对象
    q = Auth(access_key, secret_key)

    for path in paths:
        path = str(path)

        key = path
        if len(path.split('/')) > 1:
            key = path.split('/')[-1]

        token = q.upload_token(bucket_name, key, 3600)

        ret, info = put_file(token, key, path)

        print(info)


def modify_projects_abspath(mps, tag_version):
    res = []
    for p in PROJECTS_DIR:
        new_name = os.path.join(ABSPATH, p, 'target', '{}-v{}-SNAPSHOT.jar'.format(p, tag_version))
        os.rename(
            os.path.join(ABSPATH, p, 'target', '{}-1.0-SNAPSHOT.jar'.format(p)),
            new_name
        )
        if p in mps:
            res.append(new_name)
    return res


if __name__ == '__main__':
    # 获得diff之后的子模块列表
    modify_project, tag_version = get_modify_project()
    print modify_project

    upload_file(paths=modify_projects_abspath(modify_project, tag_version))
