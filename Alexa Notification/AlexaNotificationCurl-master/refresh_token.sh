REFRESH=`cat refresh.dat`
CLIENT_ID="amzn1.application-oa2-client.6174d75e2c0144a1bd0f1964fe712bb1"
CLIENT_SECRET="dae7ff77c1777b638fd53ab1960376fb89f8652c8bafc2099b251fe15f08d25c"
GRANT_TYPE="refresh_token"
REDIRECT_URI="https://localhost:9745/authresponse"

curl -X POST --data "grant_type=${GRANT_TYPE}&refresh_token=${REFRESH}&client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRET}&redirect_uri=${REDIRECT_URI}" https://api.amazon.com/auth/o2/token | tee refresh_token.log | python -c "import sys,json;t1=open('token.dat','w');t2=open('refresh.dat','w');x=sys.stdin.readline(); t1.write(json.loads(x)['access_token']);t2.write(json.loads(x)['refresh_token']);"
