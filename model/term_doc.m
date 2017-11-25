% k=2;
% [term] = textread('Term2.txt');
% [doc] = textread('Doc2.txt');
% scatter(doc(:,1),doc(:,2),'r.')
% hold on 
% scatter(term(:,1),term(:,2),'b.')
% xlim([0,0.004])
% ylim([-0.006,0.002])
% xlabel('Dimension 2')
% ylabel('Dimension 3')
% title('term-doc的二维展开图')
% legend('doc','term')
% box on
% % saveas(gcf,'term-doc2-3.png')

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

% k=2;
% [term] = textread('Term2.txt');
% [doc] = textread('Doc2.txt');
% % for i = 1:length(doc)
% %     sum = sqrt(doc(i,1)*doc(i,1)+doc(i,2)*doc(i,2));
% %     doc(i,:) = doc(i,:)/sum;
% % end
% % docMode = sqrt(doc(:,1).*doc(:,1)+doc(:,2).*doc(:,2));
% % docTheta = atan(doc(:,2)./ doc(:,1));
% scatter(doc(:,1),doc(:,2),'r.')
% % scatter(docMode,docTheta,'r.')
% % hold on 
% % scatter(term(:,1),term(:,2),'b.')
% xlim([0,0.1])
% ylim([-0.06,0.06])
% xlabel('Dimension 2')
% ylabel('Dimension 3')
% title('doc的二维展开图')
% % legend('doc','term')
% box on
% saveas(gcf,'doc-doc2-3.png')
% xlim([0,0.02])
% ylim([-0.02,0.02])
% saveas(gcf,'doc-doc2-3Small.png')

k=2;
[term] = textread('Term2.txt');
[doc] = textread('Doc2.txt');
% scatter(doc(:,1),doc(:,2),'r.')
% hold on 
scatter(term(:,1),term(:,2),'b.')
xlim([-0.01,0.01])
ylim([-0.01,0.01])
xlabel('Dimension 2')
ylabel('Dimension 3')
title('term的二维展开图')
% legend('doc','term')
box on
saveas(gcf,'term-term2-3.png')
xlim([-0.0002,0.0001])
ylim([-0.0001,0.0004])
saveas(gcf,'term-term2-3Small.png')