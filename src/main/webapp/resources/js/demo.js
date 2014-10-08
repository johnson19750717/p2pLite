/**
 * Created by thomas on 14-10-7.
 */
(function(){
    var demo = {
        config: {
            ip: '180.168.36.241',
            port: '',
            root: '/p2p',
            apiPath: '/api'
        },
        model: {
            recognizeFront: function(file) {},
            recognizeBack: function(file) {},
            getHistoryFront: function(callback) {},
            getHistoryBack: function(callback) {}
        },
        view: {
            showRecognitionResultFront: function() {},
            showRecognitionResultBack: function() {},
            showHistoryFront: function() {},
            showHistoryBack: function() {}
        },
        controller: {
            init: function() {
                var self = this;
                $('#recognize-front').on('change', self.invokeRecognitionFront);
                $('#recognize-back').on('change', self.invokeRecognitionBack);
                $('#a-history-front').on('shown.bs.tab', self.invokeHistoryDisplayFront);
                $('#a-history-back').on('shown.bs.tab', self.invokeHistoryDisplayBack);
                $('#sort-by-time').on('click', self.sortHistoryByTime());
            },
            invokeRecognitionFront: function(e) {var file = e.target.files[0];},
            handleRecognitionResultFront: function() {},
            invokeRecognitionBack: function(e) {var file = e.target.files[0];},
            handleRecognitionResultBack: function() {},
            invokeHistoryDisplayFront: function(e) {},
            handleHistoryDisplayFront: function() {},
            invokeHistoryDisplayBack: function(e) {},
            handleHistoryDisplayBack: function() {},
            sortHistoryByTime: function() {},
            history: {}
        },
        init: function() {
            this.controller.init();
        }
    };
    demo.init();
})();