k=2;
[term] = textread('Term2.txt');
[doc] = textread('Doc2.txt');
scatter(doc(:,1),doc(:,2),'r.')
hold on 
scatter(term(:,1),term(:,2),'b.')
xlim([0,0.004])
ylim([-0.006,0.002])
xlabel('Dimension 2')
ylabel('Dimension 3')
title('term-doc的二维展开图')
legend('doc','term')
box on
% saveas(gcf,'term-doc2-3.png')

% k=2;
% [term] = textread('Term2_.txt');
% [doc] = textread('Doc2_.txt');
% scatter(doc(:,1),doc(:,2),'r.')
% hold on 
% scatter(term(:,1),term(:,2),'b.')
% xlim([0,0.01])
% ylim([-0.01,0.01])
% xlabel('Dimension 1')
% ylabel('Dimension 2')
% title('term-doc的二维展开图')
% legend('doc','term')
% box on
% saveas(gcf,'term-doc1-2.png')