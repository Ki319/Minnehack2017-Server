CLIENT_ID="amzn1.application-oa2-client.6174d75e2c0144a1bd0f1964fe712bb1"
DEVICE_TYPE_ID="grandma_down"
DEVICE_SERIAL_NUMBER=123
REDIRECT_URI="https://localhost:9745/authresponse"
RESPONSE_TYPE="code"
SCOPE="alexa:all"
SCOPE_DATA="{\"alexa:all\": {\"productID\": \"$DEVICE_TYPE_ID\", \"productInstanceAttributes\": {\"deviceSerialNumber\": \"${DEVICE_SERIAL_NUMBER}\"}}}"

function urlencode() {
  perl -MURI::Escape -ne 'chomp;print uri_escape($_),"\n"'
}

AUTH_URL="https://www.amazon.com/ap/oa?client_id=${CLIENT_ID}&scope=$(echo $SCOPE | urlencode)&scope_data=$(echo $SCOPE_DATA | urlencode)&response_type=${RESPONSE_TYPE}&redirect_uri=$(echo $REDIRECT_URI | urlencode)"

echo "URL: ${AUTH_URL}"

