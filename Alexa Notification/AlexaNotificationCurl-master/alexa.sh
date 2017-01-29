#Auth token (replace with yours). 
TOKEN=`cat token.dat` 
 
# Boundary name, must be unique so it does not conflict with any data. 
BOUNDARY="BOUNDARY1234" # Compose cURL command and write to output file.
 echo "Making request..." curl -s -X POST \ -H "Authorization: Bearer ${TOKEN}" \ -H "Content-Type: multipart/form-data; boundary=${BOUNDARY}" \ --data-binary @multipart_body.txt \ https://access-alexa-na.amazon.com/v1/avs/speechrecognizer/recognize \ | perl -pe 'BEGIN{undef $/;} s/--.*Content-Type: audio\/mpeg.*(ID3.*)--.*--/$1/smg' \ | tee response.mp3 | play -t mp3 -q -
