/**
 * Created by thomas on 14-10-7.
 */
(function(){
    var demo = {
        config: {
            protocol: 'http',
            ip: '180.168.36.241',
            port: '',
            apiPath: 'api'
        },

        state: {
            version: '0.9.2',
            lastModified: 'Oct 9 2014',
            historyUpToDateFront: false,
            historyUpToDateBack: false
        },

        model: {
            recognizeIdCard: function(file, side, callback, errorHandler) {
                var formData = new FormData();
                formData.append("file", file);
                $.ajax({
                    url: demo.config.apiPath + '/idcard/' + side,
                    type: 'POST',
                    data: formData,
                    cache: false,
                    processData: false,
                    contentType: false,
                    dataType: 'json',
                    success: callback,
                    error: errorHandler
                });
            },

            readAsUrl: function(file, callback) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    var url = e.target.result;
                    callback(url);
                };
                reader.readAsDataURL(file);
            },

            getHistoryFront: function(callback) {
                $.get(demo.config.apiPath + '/idcard/front', callback, 'json');
            },

            getHistoryBack: function(callback) {
                $.get(demo.config.apiPath + '/idcard/back', callback, 'json');
            },

            convertToReadableTime: function(ms) {
                var date = new Date(parseInt(ms, 10)),
                    year = date.getFullYear(),
                    month = date.getMonth() + 1,
                    day = date.getDate(),
                    hours = date.getHours(),
                    minutes = date.getMinutes(),
                    seconds = date.getSeconds();
                return year + '年' + month + '月' + day + '日' + hours + '时' + minutes + '分';
            }
        },

        view: {
            showUrlImgFront: function(url) {
                $('#img-front').attr('src', url);
            },

            showRecognitionResultFront: function(json) {
                $('#name').html(json.name);
                $('#cardNo').html(json.cardNo);
                $('#gender').html(json.sex);
                $('#birthday').html(json.birthday);
                $('#address').html(json.address);
            },

            showUrlImgBack: function(url) {
                $('#img-back').attr('src', url);
            },

            showRecognitionResultBack: function(json) {
                $('#validPeriod').html(json.validPeriod);
                $('#releasedBy').html(json.issueAuthority);
            },

            showHistoryFront: function(json) {
                var template = '<td class="history-file-front"><a data-toggle="modal" data-target="#img-box-front">查看图片</a></td>' +
                    '<td class="history-name"></td>' +
                    '<td class="history-cardNo"></td>' +
                    '<td class="history-gender"></td>' +
                    '<td class="history-address"></td>' +
                    '<td class="recognized-at-front"></td>',
                    tbody = $('<tbody></tbody>').attr('id', 'table-history-front'),
                    tr;
                for(var i = 0, l= json.length; i < l; i++) {
                    tr = $('<tr></tr>').html(template);
                    tr.find('td[class="history-file-front"] a').attr('data-url', demo.config.apiPath + '/resources/idcard/' + json[i].source);
                    tr.find('td[class="history-name"]').html(json[i].name);
                    tr.find('td[class="history-cardNo"]').html(json[i].cardNo);
                    tr.find('td[class="history-gender"]').html(json[i].sex);
                    tr.find('td[class="history-address"]').html(json[i].address);
                    tr.find('td[class="recognized-at-front"]').html(demo.model.convertToReadableTime(json[i].uploadDate));
                    tbody.append(tr);
                }
                $('#table-history-front').replaceWith(tbody);
            },

            showHistoryBack: function(json) {
                var template = '<td class="history-file-back"><a data-toggle="modal" data-target="#img-box-back">查看图片</a></td>' +
                    '<td class="history-valid-period"></td>' +
                    '<td class="history-released-by"></td>' +
                    '<td class="recognised-at-back"></td>',
                    tbody = $('<tbody></tbody>').attr('id', 'table-history-back'),
                    tr;
                for(var i = 0, l= json.length; i < l; i++) {
                    tr = $('<tr></tr>').html(template);
                    tr.find('td[class="history-file-back"] a').attr('data-url', demo.config.apiPath + '/resources/idcard/' + json[i].source);
                    tr.find('td[class="history-valid-period"]').html(json[i].validPeriod);
                    tr.find('td[class="history-released-by"]').html(json[i].issueAuthority);
                    tr.find('td[class="recognized-at-back"]').html(demo.model.convertToReadableTime(json[i].uploadDate));
                    tbody.append(tr);
                }
                $('#table-history-back').replaceWith(tbody);
            },

            showLoadingImg: function() {
                $('#loading').show();
            },

            hideLoadingImg: function() {
                $('#loading').hide();
            }
        },

        controller: {
            init: function() {
                var self = this;
                $('#recognize-front').on('change', self.invokeRecognitionFront);
                $('#recognize-back').on('change', self.invokeRecognitionBack);
                $('#a-history-front').on('shown.bs.tab', self.invokeHistoryDisplayFront);
                $('#a-history-back').on('shown.bs.tab', self.invokeHistoryDisplayBack);
            },

            invokeRecognitionFront: function(e) {
                demo.state.historyUpToDateFront = false;
                demo.view.showLoadingImg();
                var file = e.target.files[0];
                demo.model.recognizeIdCard(file, 'front', demo.controller.handleRecognitionResultFront, demo.controller.ajaxErrorHandler);
                demo.model.readAsUrl(file, demo.view.showUrlImgFront);
            },

            handleRecognitionResultFront: function(json) {
                demo.view.showRecognitionResultFront(json);
                demo.view.hideLoadingImg();
            },

            invokeRecognitionBack: function(e) {
                demo.state.historyUpToDateBack = false;
                demo.view.showLoadingImg();
                var file = e.target.files[0];
                demo.model.recognizeIdCard(file, 'back', demo.controller.handleRecognitionResultBack, demo.controller.ajaxErrorHandler);
                demo.model.readAsUrl(file, demo.view.showUrlImgBack);
            },

            handleRecognitionResultBack: function(json) {
                demo.view.showRecognitionResultBack(json);
                demo.view.hideLoadingImg();
            },

            ajaxErrorHandler: function(xmlHttpRequest) {
                demo.view.hideLoadingImg();
                console.log(xmlHttpRequest);
            },

            invokeHistoryDisplayFront: function() {
                if(!demo.state.historyUpToDateFront) {
                    demo.model.getHistoryFront(demo.controller.handleHistoryDisplayFront);
                    demo.state.historyUpToDateFront = true;
                }
            },

            handleHistoryDisplayFront: function(json) {
                demo.view.showHistoryFront(json);
                demo.controller.registerImgModal('front');
            },

            invokeHistoryDisplayBack: function() {
                if(!demo.state.historyUpToDateBack) {
                    demo.model.getHistoryBack(demo.controller.handleHistoryDisplayBack);
                    demo.state.historyUpToDateBack = true;
                }
            },

            handleHistoryDisplayBack: function(json) {
                demo.view.showHistoryBack(json);
                demo.controller.registerImgModal('back');
            },

            registerImgModal: function(side) {
                side = side.toLowerCase();
                var selector = 'a[data-target="#img-box-' + side + '"]',
                    img = '#img-box-' + side + ' img';
                $(selector).on('click', function() {
                    $(img).attr('src', this.dataset.url);
                });
            }
        },

        init: function() {
            this.controller.init();
        }
    };

    demo.init();
})();