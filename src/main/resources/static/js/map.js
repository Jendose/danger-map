var coordForAdd=[]; //координаты по клику на карту.
var myPlacemark; //метка по клику на карту.
var geolocation = ymaps.geolocation;
var map; //карта
var newMark;
var arrValueNewMark;
var temps=[];
var zones = new Map([
  ['BIOLOGICAL_HAZARD',  'Биологически опасная зона'],
  ['GEOLOGICAL_HAZARD',    'Геологически опасная зона'],
  ['AIR_POLLUTION', 'Загрязнение воздуха'],
  ['WATER_POLLUTION', 'Загрязнение воды'],
  ['CHEMICAL_HAZARD', 'Химически опасная зона'],
  ['RADIATION_HAZARD', 'Радиационно опасная зона'],
  ['INDUSTRIAL_AREA', 'Промышленная зона']
]);
var attributes=['Количество радиоактивных отходов',
				'Количество перерабатываемых отходов',
				'Количество неперерабатываемых отходов',
				'Средняя температура',
				'Средняя посещаемость области',
				'Процент загрязнение воды',
				'Процент загрязнение воздуха',
				'Процент загрязнение почвы',
				'Степень разрушенности пород (1-10)',
				'Количество промышленных предприятий на территории',
				'Наличие водоема рядом',
				'Работа с легковоспламеняющимися веществами'];

//массив меток, которые приходят с бека:
var placemarks=[];
function weatherMark(lat,lon,i){
	var str='http://api.openweathermap.org/data/2.5/weather?lat=';
	str=str+lat+'&lon='+lon+'&appid=f777240c1df04c003483ae007b5c6958';
		fetch(str)
			.then(function (resp){return resp.json() })
			.then(function(data){
				var temp=data.main.temp-273.15;
				var s='<li class="list-group-item">T(C) = '+temp+'</li></ul>';
				temps[i]=s;
		
			})
			.catch(function (){

			});
	};
//for(var i=0; i<placemarks.length;i++){ weatherMark(placemarks[i].latitude,placemarks[i].longitude,i);};
$.ajax({
    contentType: "application/json; charset=UTF-8",
    dataType: 'html',
    type: 'GET',
    url: '/get_hazardous_zone_list',
    headers: {
        'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
    },
    success: function(data) {
        placemarks=JSON.parse(data);
		for(var i=0; i<placemarks.length;i++){ weatherMark(placemarks[i].latitude,placemarks[i].longitude,i);};
		console.log(temps);
        ymaps.ready(init);
    }
});
//ymaps.ready(init);



geoObjects=[]; //массив меток, которые на карте из данных с бд.

//создание карты:
function init(){
	for (var i = 0; i < placemarks.length; i++) {
		placemarks[i].hazardousZoneType
	}

    console.log(temps);
	var inputSearch = new ymaps.control.SearchControl({
        options: {
            // Пусть элемент управления будет
            // в виде поисковой строки.
            size: 'large',
            // Включим возможность искать
            // не только топонимы, но и организации.
            provider: 'yandex#search'            
        }
    });
	map=new ymaps.Map('map', {
		center:[51.53,45.99],
		zoom: 12,
		controls: ['default', 'routeButtonControl']
	}, {
            searchControlProvider: 'yandex#search'
        });

	//метки на карту:
	var str='';
	for(var i=0; i<placemarks.length;i++){
		str='<ul class="list-group">';
		str=str+'<li class="list-group-item">Местоположение: '+placemarks[i].name+'</li>';
		s(placemarks[i]);
		for(var j=0; j<attributes.length;j++){
			str=str+'<li class="list-group-item">'+attributes[j]+
				' = '+arrValueNewMark[j]+'</li>';
		}
		str=str+'<li class="list-group-item">Тип зоны'+
				' = '+zones.get(placemarks[i].hazardousZoneType)+'</li>'+temps[i];

		str=str+'<button type="button" onclick="deleteMark('+placemarks[i].latitude+','+placemarks[i].longitude+')" class="btn btn-danger">Удалить</button>';


		console.log(temps);
		console.log(temps[i]);
		geoObjects[i]=new ymaps.Placemark([placemarks[i].latitude,placemarks[i].longitude],{
						hintContent:placemarks[i].name,
						balloonContent: str
					},{
						iconLayout:'default#image'
					});
		
	}
	var clusterer=new ymaps.Clusterer({

		});
	map.geoObjects.add(clusterer);
	clusterer.add(geoObjects);

	




	
	// Слушаем клик на карте.
    map.events.add('click', function (e) {
        var coords = e.get('coords');
        coordForAdd=coords;
        // Если метка уже создана – просто передвигаем ее.
        if (myPlacemark) {
            myPlacemark.geometry.setCoordinates(coords);
        }
        // Если нет – создаем.
        else {
            myPlacemark = createPlacemark(coords);
            map.geoObjects.add(myPlacemark);
            // Слушаем событие окончания перетаскивания на метке.
            myPlacemark.events.add('dragend', function () {
                getAddress(myPlacemark.geometry.getCoordinates());
            });
        }
        getAddress(coords);
        console.log(coords);
     });

    // Создание метки.
    function createPlacemark(coords) {
        return new ymaps.Placemark(coords, {
            iconCaption: 'поиск...'
        }, {
            preset: 'islands#violetDotIconWithCaption',
            draggable: true
        });
    }

    // Определяем адрес по координатам (обратное геокодирование).
    function getAddress(coords) {
        myPlacemark.properties.set('iconCaption', 'поиск...');
        ymaps.geocode(coords).then(function (res) {
            var firstGeoObject = res.geoObjects.get(0);

            myPlacemark.properties
                .set({
                    // Формируем строку с данными об объекте.
                    iconCaption: [
                        // Название населенного пункта или вышестоящее административно-территориальное образование.
                        firstGeoObject.getLocalities().length ? firstGeoObject.getLocalities() : firstGeoObject.getAdministrativeAreas(),
                        // Получаем путь до топонима, если метод вернул null, запрашиваем наименование здания.
                        firstGeoObject.getThoroughfare() || firstGeoObject.getPremise()
                    ].filter(Boolean).join(', '),
                    // В качестве контента балуна задаем строку с адресом объекта.
                    balloonContent: firstGeoObject.getAddressLine()
                });
        });
    }
}

//модальное окно при нажатии на кнопку "Добавить метку"
function addPlace(){
	var namePole='Какое-то поле';
	var tagPole='<div class="input-group input-group-sm mb-3">'+
				'<div class="input-group-prepend">'+'<span class="input-group-text" id="inputGroup-sizing-sm">'+namePole+'</span></div>'+
  				'<input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"></div>';
	if (coordForAdd.length>0){
		console.log(myPlacemark);
		var str='<div>Местоположение: <span>'+myPlacemark.properties._data.balloonContent+'</span></div>'+
				'<div>Широта: <span>'+coordForAdd[0]+'</span></div>'+
				'<div>Долгота: <span>'+coordForAdd[1]+'</span></div></br>';
		newMark={
			name: myPlacemark.properties._data.balloonContent,
			latitude:coordForAdd[0],
			longitude:coordForAdd[1]
		}
		for(var i=0;i<attributes.length;i++){
			str=str+'<div class="input-group input-group-sm mb-3">'+
				'<div class="input-group-prepend">'+'<span class="input-group-text" id="inputGroup-sizing-sm">'+attributes[i]+'</span></div>'+
  				'<input type="text" class="form-control inp_class" aria-label="Small" aria-describedby="inputGroup-sizing-sm"></div>';
		}
		document.getElementById('dscprtNewPlace').innerHTML=str;
		$('#staticBackdrop').modal();

		//$('#staticBackdrop').modal('hide');
	} else alert('Выберите точку на карте');
}
//добавление метки в модальном окне добавления.
function addMarkPlace(){
	var s=getBalloonContent();
	placemarks.push(newMark);
	var ob={
		latitude:Number(newMark.latitude),
		longitude:Number(newMark.longitude),
		hintContent:newMark.name,
		balloonContent: s
	};

	var str='http://api.openweathermap.org/data/2.5/weather?lat=';
		str=str+ob.latitude+'&lon='+ob.longitude+'&appid=f777240c1df04c003483ae007b5c6958';
	console.log(str);
	fetch(str)
		.then(function (resp){return resp.json() })
		.then(function(data){
			temp=data.main.temp-273.15;
			placemarks[placemarks.length-1].balloonContent=ob.balloonContent+'<li class="list-group-item">T(C) = '+
			temp+'</li></ul>'+'<button type="button" onclick="deleteMark('+placemarks[placemarks.length-1].latitude+','+placemarks[placemarks.length-1].longitude+')" class="btn btn-danger">Удалить</button>';
			console.log(placemarks);
			ob.balloonContent=placemarks[placemarks.length-1].balloonContent;
			console.log(placemarks[placemarks.length-1].balloonContent);
			console.log(ob.balloonContent);
			var plmark=createAndAddPlacemark([ob.latitude,ob.longitude],ob);
			map.geoObjects.add(plmark);
			console.log(placemarks);
			var ManageHazardousZoneDto={
				name:newMark.name,
				latitude:Number(newMark.latitude),
				longitude:Number(newMark.longitude),
				nuclearWasteAmount: arrValueNewMark[0],
				recycledWasteAmount: arrValueNewMark[1],
				unrecyclableWasteAmount: arrValueNewMark[2],
				averageTemperature: arrValueNewMark[3],
				averageAttendance: arrValueNewMark[4],
				waterPollutionRate: arrValueNewMark[5],
				airPollutionRate: arrValueNewMark[6],
				soilPollutionRate: arrValueNewMark[7],
				rockDestructionLevel: arrValueNewMark[8],
				industrialEnterprisesNumber: arrValueNewMark[9],
				reservoirExist: arrValueNewMark[10],
				flammableSubstancesExist: arrValueNewMark[11],
				hazardousZoneType: newMark.hazardousZoneType

			};
			console.log(ManageHazardousZoneDto);
			$('#staticBackdrop1').modal('hide');

			$.ajax({
		        contentType: "application/json; charset=UTF-8",
		        dataType: 'html',
		        type: 'POST',
		        url: '/add_hazardous_zone',
		        data: JSON.stringify(ManageHazardousZoneDto),
		        headers: {
		            'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
		        },
		        success: function(data) {
					placemarks[placemarks.length-1].id=Number(data);
		            //$("#track-list").html(data);
		        }
    		});
		})
		.catch(function (){

		});
}

function createAndAddPlacemark(coords,ob) {
        return new ymaps.Placemark(coords, {
			hintContent:ob.hintContent,
			balloonContent: ob.balloonContent
		},{
			iconLayout:'default#image',
			//iconImageHref:'img/map-marker.png',
			//iconImageSize: [50,50],
			//iconImageOffset:[-23,-57]
		});
    }
 function deleteMark(lat,lon){
 	//var collection = new ymaps.GeoObjectCollection(null, { preset: group.style });


 	

  	var id;
 	map.geoObjects.removeAll();
 	var placemarks1=[];
 	console.log(placemarks);
 	for(var i=0;i<placemarks.length;i++){
 		if(placemarks[i].latitude==lat&&placemarks[i].longitude==lon) {

 			id=placemarks[i].id;
 			console.log(placemarks.length);
 			placemarks.splice(i,1);
			console.log(placemarks.length);
 			temps.splice(i,1);
 		}
 	};

	$.ajax({
		        contentType: "application/json; charset=UTF-8",
		        dataType: 'html',
		        type: 'GET',
		        url: '/delete_hazardous_zone/' + id,
		        // data: id,
		        headers: {
		            'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
		        },
		        success: function(data) {
		            //$("#track-list").html(data);
		        }
    		});
 	console.log(placemarks);
 	console.log(id);
	geoObjects=[];
 	for(var i=0; i<placemarks.length;i++){
		geoObjects[i]=new ymaps.Placemark([placemarks[i].latitude,placemarks[i].longitude],{
			hintContent:placemarks[i].hintContent
		},{
			iconLayout:'default#image',
		});
		
	};
	var clusterer=new ymaps.Clusterer({

	});
	//map.geoObjects.add(placemark);
	map.geoObjects.add(clusterer);
	if(myPlacemark) map.geoObjects.add(myPlacemark);
	// delete myPlacemark;
	clusterer.add(geoObjects);
	drawMarks();
	// location.reload();



 }





function checkNeuro(){
	$('#staticBackdrop').modal('hide');
	var elements = document.getElementsByClassName('inp_class');
	var arr=[]
	for(var i=0;i<elements.length;i++) arr.push(parseFloat(elements[i].value));
	arrValueNewMark=arr;
	//отправляем массив на бек в нейронку

	//var rez='Промышленные зоны'
	newMark.nuclearWasteAmount=arr[0];
	newMark.recycledWasteAmount=arr[1];
	newMark.unrecyclableWasteAmount=arr[2];
	newMark.averageTemperature=arr[3];
	newMark.averageAttendance=arr[4];
	newMark.waterPollutionRate=arr[5];
	newMark.airPollutionRate=arr[6];
	newMark.soilPollutionRate=arr[7];
	newMark.rockDestructionLevel=arr[8];
	newMark.industrialEnterprisesNumber=arr[9];
	newMark.reservoirExist=arr[10];
	newMark.flammableSubstancesExist=arr[11];

	var ManageHazardousZoneDto={
				name:'',
				latitude:Number(newMark.latitude),
				longitude:Number(newMark.longitude),
				nuclearWasteAmount: arrValueNewMark[0],
				recycledWasteAmount: arrValueNewMark[1],
				unrecyclableWasteAmount: arrValueNewMark[2],
				averageTemperature: arrValueNewMark[3],
				averageAttendance: arrValueNewMark[4],
				waterPollutionRate: arrValueNewMark[5],
				airPollutionRate: arrValueNewMark[6],
				soilPollutionRate: arrValueNewMark[7],
				rockDestructionLevel: arrValueNewMark[8],
				industrialEnterprisesNumber: arrValueNewMark[9],
				reservoirExist: arrValueNewMark[10],
				flammableSubstancesExist: arrValueNewMark[11],
				hazardousZoneType: 'AIR_POLLUTION'

			};
			$.ajax({
		        contentType: "application/json; charset=UTF-8",
		        dataType: 'html',
		        type: 'POST',
		        url: '/check_hazardous_zone',
		        data: JSON.stringify(ManageHazardousZoneDto),
		        headers: {
		            'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
		        },
		        success: function(data) {
		        	for (var [key, value] of zones) {
		        		if(key==data){
							document.getElementById('rezNeuro').innerHTML=value;
							newMark.hazardousZoneType = data;
							$('#staticBackdrop1').modal();
							break;
		        		}
					}
		        }
    		});

}

function s(ob){
	arrValueNewMark=[];
	arrValueNewMark.push(ob.nuclearWasteAmount);
	arrValueNewMark.push(ob.recycledWasteAmount);
	arrValueNewMark.push(ob.unrecyclableWasteAmount);
	arrValueNewMark.push(ob.averageTemperature);
	arrValueNewMark.push(ob.averageAttendance);
	arrValueNewMark.push(ob.waterPollutionRate);
	arrValueNewMark.push(ob.airPollutionRate);
	arrValueNewMark.push(ob.soilPollutionRate);
	arrValueNewMark.push(ob.rockDestructionLevel);
	arrValueNewMark.push(ob.industrialEnterprisesNumber);
	arrValueNewMark.push(ob.reservoirExist);
	arrValueNewMark.push(ob.flammableSubstancesExist);
}

function getBalloonContent(){
	str='<ul class="list-group">';
	str=str+'<li class="list-group-item">Местоположение: '+newMark.name+'</li>';
	for(var i=0; i<arrValueNewMark.length;i++){
		str=str+'<li class="list-group-item">'+attributes[i]+
			' = '+arrValueNewMark[i]+'</li>';
	}
	str=str+'<li class="list-group-item">Тип зоны'+
		' = '+zones.get(newMark.hazardousZoneType)+'</li>';
	return str;
};

function drawMarks(){

	var str='';
	for(var i=0; i<placemarks.length;i++){
		str='<ul class="list-group">';
		str=str+'<li class="list-group-item">Местоположение: '+placemarks[i].name+'</li>';
		s(placemarks[i]);
		for(var j=0; j<attributes.length;j++){
			str=str+'<li class="list-group-item">'+attributes[j]+
				' = '+arrValueNewMark[j]+'</li>';
		}
		str=str+'<li class="list-group-item">Тип зоны'+
			' = '+zones.get(placemarks[i].hazardousZoneType)+'</li>'+temps[i];

		console.log(temps);
		str=str+temps[i];
		str=str+'<button type="button" onclick="deleteMark('+placemarks[i].latitude+','+placemarks[i].longitude+')" class="btn btn-danger">Удалить</button>';
		//str=str+'<li class="list-group-item">t(C)='+temp+'</li></ul>'+'<button type="button" onclick="deleteMark('+placemarks[i].latitude+','+placemarks[i].longitude+')" class="btn btn-danger">Удалить</button>';
		
		geoObjects[i]=new ymaps.Placemark([placemarks[i].latitude,placemarks[i].longitude],{
						hintContent:placemarks[i].name,
						balloonContent: str
					},{
						iconLayout:'default#image'
					});
		
	}
	var clusterer=new ymaps.Clusterer({

		});

	//map.geoObjects.add(placemark);
	map.geoObjects.add(clusterer);
	clusterer.add(geoObjects);

}