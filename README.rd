Comanda post terminal:
	curl -X POST -d '{"longitude":"10.00","latitude":"20.00","usuario":"gustavo"}' 'https://tg-novo.firebaseio.com/coordenates.json'

curl -X PATCH -d '{
  "data": {"latitude": "20.00", "longitude":"10.00","usuario":"robson"}
}'   'https://tg-novo.firebaseio.com/coordenates.json'
{"data":{"latitude":"20.00","longitude":"10.00","usuario":"robson"}}
