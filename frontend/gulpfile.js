'use strict';

var gulp = require('gulp');
var less = require('gulp-less');
var minify = require('gulp-minify-css');

gulp.task('less', function () {
    return gulp.src('./src/less/**/*.less')
        .pipe(less({
            paths: [
                './src/css/'
            ]
        }))
        .pipe(minify({processImport: false}))
        .pipe(gulp.dest('./build/dist/css/'));
});

gulp.task('build', ['less']);
