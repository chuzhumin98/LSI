% k=3;
% [term] = textread('Term3.txt');
% [doc] = textread('Doc3.txt');
% scatter3(doc(:,1),doc(:,2),doc(:,3),'r.')
% hold on 
% scatter3(term(:,1),term(:,2),term(:,3),'b.')
% xlim([-0.01,0.01])
% ylim([-0.01,0.01])
% zlim([-0.01,0.01])
% xlabel('Dimension 2')
% ylabel('Dimension 3')
% zlabel('Dimension 4')
% title('term-doc的三维展开图')
% legend('doc','term')
% box on
% saveas(gcf,'term-doc2-3-4.png')

% k=3;
% [term] = textread('Term3.txt');
% [doc] = textread('Doc3.txt');
% % for i = 1:length(doc)
% %     sum = sqrt(doc(i,1)*doc(i,1)+doc(i,2)*doc(i,2));
% %     doc(i,:) = doc(i,:)/sum;
% % end
% % docMode = sqrt(doc(:,1).*doc(:,1)+doc(:,2).*doc(:,2));
% % docTheta = atan(doc(:,2)./ doc(:,1));
% scatter3(doc(:,1),doc(:,2),doc(:,3),'r.')
% % scatter(docMode,docTheta,'r.')
% % hold on 
% % scatter(term(:,1),term(:,2),'b.')
% xlim([0,0.1])
% ylim([-0.06,0.06])
% zlim([-0.1,0.05])
% xlabel('Dimension 2')
% ylabel('Dimension 3')
% zlabel('Dimension 4')
% title('doc的三维展开图')
% % legend('doc','term')
% box on
% saveas(gcf,'doc-doc2-3-4.png')
% xlim([0,0.02])
% ylim([-0.02,0.02])
% zlim([-0.1,0.05])
% saveas(gcf,'doc-doc2-3-4Small.png')

k=3;
[term] = textread('Term3.txt');
[doc] = textread('Doc3.txt');
% scatter(doc(:,1),doc(:,2),'r.')
% hold on 
scatter3(term(:,1),term(:,2),term(:,3),'b.')
xlim([-0.01,0.01])
ylim([-0.01,0.01])
zlim([-0.01,0.01])
xlabel('Dimension 2')
ylabel('Dimension 3')
zlabel('Dimension 4')
title('term的三维展开图')
% legend('doc','term')
box on
saveas(gcf,'term-term2-3-4.png')
xlim([-0.0002,0.0001])
ylim([-0.0001,0.0004])
zlim([-0.0003,0.0003])
saveas(gcf,'term-term2-3-4Small.png')