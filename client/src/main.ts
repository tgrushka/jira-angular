import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';
import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

declare var AJS: any;

const bootstrap = () => {
  platformBrowserDynamic()
    .bootstrapModule(AppModule)
    .catch(err => console.error(err));
};

if (environment.production) {
  enableProdMode();
}

if (typeof AJS !== 'undefined') {
  AJS.toInit(() => {
    bootstrap();
  });
} else {
  bootstrap();
}
