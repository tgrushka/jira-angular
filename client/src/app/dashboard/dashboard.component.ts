import { Component, OnInit } from '@angular/core';
import { Hero } from '../hero';
import { HeroService } from '../hero.service';
import { AJS } from '@atlassian/aui/dist/aui/aui-prototyping.js';

declare var AJS: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  heroes: Hero[] = [];

  dialog: any;

  icons: string[] = ['activity', 'add-circle', 'add-comment', 'add-item', 'add', 'addon', 'advanced', 'app-access', 'app-switcher', 'approve', 'arrow-down-circle', 'arrow-down-left', 'arrow-down-right', 'arrow-down-small', 'new-arrow-down', 'arrow-left-circle', 'arrow-left', 'arrow-right-circle', 'arrow-right', 'arrow-up-circle', 'arrow-up-small', 'new-arrow-up', 'attachment', 'audio-circle', 'audio', 'backlog', 'billing-filled', 'billing', 'board', 'bold', 'book', 'branch', 'bullet-list', 'calendar-filled', 'calendar', 'camera-filled', 'camera-rotate', 'camera-take-picture', 'camera', 'canvas', 'cell-color-underline', 'cell-color', 'center-alignment', 'check-circle-filled', 'check-circle', 'check', 'checkbox', 'chevron-double-down', 'chevron-double-left', 'chevron-double-right', 'chevron-double-up', 'chevron-down-circle', 'chevron-down', 'chevron-left-circle', 'chevron-left', 'chevron-right-circle', 'chevron-right', 'chevron-up-circle', 'chevron-up', 'clone-small', 'code', 'comment', 'commits', 'component', 'confluence', 'copy-table-column', 'copy-table-row', 'copy', 'create-branch', 'create-fork', 'create-pull-request', 'credit-card', 'credit-card-filled', 'cross-circle', 'cross', 'cut-table-column', 'cut-table-row', 'dashboard', 'decision', 'detail-view', 'discover-filled', 'discover', 'document-filled', 'document', 'documents', 'download', 'dropbox', 'edit-filled', 'new-edit', 'email', 'emoji', 'error', 'export', 'failed-build', 'feedback', 'file', 'filter', 'flag', 'folder-filled', 'folder', 'followers', 'following', 'fork-small', 'gallery', 'google-drive', 'google', 'graph-bar', 'graph-line', 'group', 'heading-column', 'heading-row', 'new-help', 'highlights', 'home-circle', 'home-filled', 'horizontal-rule', 'image-border', 'image-resize', 'image', 'import', 'incomplete-build', 'indent-left', 'indent-right', 'info-circle', 'info-filled', 'insert-column-after', 'insert-column-before', 'insert-numbered-column', 'insert-row-after', 'insert-row-before', 'invite-team', 'issue-raise', 'issue', 'issues', 'italic', 'jira', 'left-alignment', 'left-side-bar', 'lightbulb-filled', 'lightbulb', 'like', 'link-filled', 'link', 'list', 'location', 'lock-circle-small', 'lock-filled', 'lock', 'marketplace', 'mention', 'menu', 'merge-table-cells', 'more-vertical', 'more', 'multiple-commits', 'needs-work', 'notification-all', 'notification-direct', 'notification', 'number-list', 'office-building-filled', 'office-building', 'open', 'overview', 'page-filled', 'page-layout-toggle', 'page', 'paint-bucket', 'paste-table-column', 'paste-table-row', 'pdf', 'people-group', 'people', 'person-circle', 'person', 'plan-disabled', 'portfolio', 'preferences', 'progress', 'pull-requests', 'question-circle', 'question-filled', 'queued-build', 'queues', 'quote', 'radio', 'recent-filled', 'recent', 'redo', 'refresh', 'remove-column', 'remove-row', 'remove-table', 'repository-small', 'right-alignment', 'right-side-bar', 'room-menu', 'running-build', 'schedule-filled', 'schedule', 'screen', 'search', 'send', 'settings', 'share', 'ship', 'shortcut', 'sign-in', 'sign-out', 'single-column', 'source', 'split-merged-table-cells', 'star-filled', 'new-star', 'submodule', 'subtask', 'successful-build', 'swap', 'symbol', 'table-of-contents', 'table', 'tag', 'task-list', 'task', 'team-calendar', 'text-color-underline', 'text-color', 'three-column-side-bars', 'three-column', 'trash', 'tray-empty', 'tray', 'two-column', 'underline', 'undo', 'unlink', 'unlock-circle', 'unlock-filled', 'unlock', 'upload', 'vid-audio-muted', 'vid-audio-on', 'vid-backward', 'vid-forward', 'vid-full-connection-circle', 'vid-full-screen-off', 'vid-full-screen-on', 'vid-full-speaking-circle', 'vid-hang-up', 'vid-hd-circle', 'vid-pause', 'vid-play', 'vid-raised-hand', 'vid-share-screen', 'video-camera-off', 'video-circle', 'video-filled', 'warning', 'watch-filled', 'new-watch', 'world', 'close-dialog'];
  colors: string[] = ['#c00', '#880', '#088', '#808', '#00c', '#0a0'];
  messages: string[] = ['info', 'error', 'warning', 'confirmation', 'change'];
  table: any;

  constructor(private heroService: HeroService) { }

  ngOnInit() {
    this.getHeroes();
    this.dialog = AJS.dialog2('#demo-dialog');

    AJS.$('[title]').tooltip();

    AJS.tablessortable.setTableSortable(AJS.$('.aui-table-sortable'));

    for (let message of this.messages) {
        eval('AJS.messages.' + message)("#demo-messages-container", {
            title: 'This is a title in a ' + message + ' message.',
            body: '<p> And this is just content in a ' + message + ' message.</p>'
        });
    }

    AJS.tabs.setup();

    this.table = new AJS.RestfulTable({
        el: "#demo-restful-table",
        autoFocus: true,
        resources: {
            all: AJS.params.baseURL + "/rest/heroes/1.0/heroes",
            self: AJS.params.baseURL + "/rest/heroes/1.0/heroes"
        },
        deleteConfirmationCallback: function(model) {
            AJS.$("#delete-confirmation-model")[0].innerHTML = "<b>ID:</b> " + model.id + " <b>name:</b> " + model.name;
            AJS.dialog2("#delete-confirmation-dialog").show();
            return new Promise(function(resolve, reject) {
                AJS.$("#delete-confirmation-submit-button").on('click', function (e) {
                    resolve();
                    e.preventDefault();
                    AJS.dialog2("#delete-confirmation-dialog").hide();
                });
                AJS.$(".aui-dialog2-header-close, #warning-dialog-cancel").on('click', function (e) {
                    reject();
                    e.preventDefault();
                    AJS.dialog2("#delete-confirmation-dialog").hide();
                });
            });
        },
        columns: [
            {
                id: "id",
                header: "ID",
                allowEdit: false
            },
            {
                id: "name",
                header: "Name"
            }
        ]
    });
  }

  getHeroes(): void {
    this.heroService.getHeroes()
      .subscribe(heroes => this.heroes = heroes.slice(0, 5));
  }

  showFlag(): void {
    let myFlag = AJS.flag({
        type: 'success',
        body: 'Issue ADG-745 has been created.',
    });
  }
}
