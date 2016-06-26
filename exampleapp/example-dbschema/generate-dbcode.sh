#!/bin/bash

curdir=$(cd $(dirname ${BASH_SOURCE[0]}); pwd)
cd ${curdir}

exec mvn -f dbschema-pom.xml clean flyway:migrate querydsl:export
