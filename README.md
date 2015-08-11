# wsdl-dump
Dump WSDLs and all dependencies to file, preserving the remote structures

## Usage
Build the project with maven:

    $ mvn clean install package appassembler:assemble

This produces a `wsdldump` script that you can use:

    $ ./wsdldump "http://domain.tld/ws/my/service/api/path?wsdl"

The structure of the created files will be that of the URL, without the domain part, so the following will be the first case for the original wsdl file:

    $ ./ws/my/service/api/path
