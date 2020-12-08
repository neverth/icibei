import os

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

    modify_files = os.popen('git diff --stat {} {}'.format(tags[0], tags[1])).read().split('\n')

    for modify_file in modify_files[0: -2]:
        dir_name = modify_file.split('/')[0]

        if dir_name in PROJECTS_DIR:
            modify_project.append(dir_name)

    return modify_project


if __name__ == '__main__':
    get_modify_project()
