var gulp = require('gulp');
var lintspaces = require('gulp-lintspaces');

gulp.task('lint', function() {
	return gulp.src(['./**/*.java'])
		.pipe(lintspaces({
			trailingspaces: true,
			indentation: 'tabs',
			newlineMaximum: 2,
			ignores: [
				'java-comments'
			]
		}))
		.pipe(lintspaces.reporter());
});
